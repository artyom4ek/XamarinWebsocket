using Android.App;
using Android.Widget;
using Android.OS;
using Websockets;
using System.Threading.Tasks;

namespace XamarinWebsocket
{
	/*
 	 * Главная активность проекта.
 	 * Используем Websockets.PCL для работы с веб-соккетами.
 	 * @Created by Тёма on 08.08.2017.
 	 * @version 1.0
 	 */
	[Activity(Label = "XamarinWebsocket", MainLauncher = true, Icon = "@mipmap/icon")]
	public class MainActivity : Activity
	{ 
	
		private IWebSocketConnection webSocketConnection;
		private bool echo, failed;
		private TextView dataText;
		private EditText editMessageText;

		protected override void OnCreate(Bundle savedInstanceState)
		{
			base.OnCreate(savedInstanceState);

			// Устанавливаем главный слой.
			SetContentView(Resource.Layout.Main);

			// Устанавливаем настройки Websocket.
			SetupConnection();

			// Получаем айдишники компонентов активности.
			dataText = FindViewById<TextView>(Resource.Id.data_text);
			editMessageText = FindViewById<EditText>(Resource.Id.edit_message_text);
			Button button = FindViewById<Button>(Resource.Id.send_message_button);
			
			// Привязываем делегат к кнопке.
			button.Click += delegate {
				Send();
			};
		}

		/*
		 * Метод установки стандартной конфигурации Websocket.PCL.
         */
		void Configure()
		{
			Websockets.Droid.WebsocketConnection.Link();
		}

		/*
		 * Метод настройки подключения и создания фабрики.
		 */
		void Connect()
		{
			webSocketConnection = Websockets.WebSocketFactory.Create();
			webSocketConnection.OnOpened += Connection_OnOpened;
			webSocketConnection.OnError += Connection_OnError;
			webSocketConnection.OnMessage += Connection_OnMessage;
		}

		/*
		 * Метод открывает подключение к серверу.
		 */
		protected async void OpenConnection()
		{
			echo = failed = false;
			// Задаем ip и порт для доступа к серверу.
			webSocketConnection.Open("ws://192.168.0.101:7070");
			// webSocketConnection.Open("ws://echo.websocket.org");

			while (!webSocketConnection.IsOpen && !failed)
			{
				await Task.Delay(10);
			}

			if (!webSocketConnection.IsOpen)
				RunOnUiThread(() => { dataText.Text = dataText.Text + string.Format("NOT Connected!\n"); });
			else
				RunOnUiThread(() => { dataText.Text = dataText.Text + string.Format("Connected!\n"); });
		}
 
		/*
		 * Метод OnOpened возвращает текст открытия соединения.
		 */
		void Connection_OnOpened() {
			// RunOnUiThread - run from another thread
			RunOnUiThread(() => { dataText.Text = dataText.Text + string.Format("Connect opened.\n");});
		}
 
		/*
		 * Метод OnError возвращает текст ошибки из веб-соккета.
		 */
		void Connection_OnError(string obj)
		{
			failed = true;
			RunOnUiThread(() => { dataText.Text = dataText.Text + string.Format("Connected error " + obj + "\n"); });
		}
 
		/*
		 * Метод OnMessage возвращает полученное сообщение.
		 */
		void Connection_OnMessage(string obj) {
			echo = true;
			RunOnUiThread(() => { dataText.Text = dataText.Text + obj + "\n"; });
		}
 
		/*
		 * Метод устанавливает общие настройки подключения к веб-соккету.
		 */
		void SetupConnection() {
			Configure();
			Connect();
			OpenConnection();
		}
 
		/*
		 * Метод открывает веб-соккет и посылает сообщение.
		 */
		async void Send()
		{
			echo = false;
			webSocketConnection.Send(editMessageText.Text);
			editMessageText.Text = "";

			while (!echo && !failed)
			{
				await Task.Delay(10);
			}

		}
	}
}


