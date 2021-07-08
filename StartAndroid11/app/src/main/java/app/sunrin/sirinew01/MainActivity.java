package app.sunrin.sirinew01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    TextToSpeech tts;
    Context context;
    TextView textView2;

   PermissionListener permissionlistener = new PermissionListener() {
       @Override
       public void onPermissionGranted() {
            initView();
       }

       @Override
       public void onPermissionDenied(ArrayList<String> deniedPermissions) {
           Toast.makeText(MainActivity.this, "권한 허용을 하지 않으면 서비스를 이용할 수 없습니다.", Toast.LENGTH_SHORT).show();
       }
   };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;


        textView2 = findViewById(R.id.textView2);
        tts = new TextToSpeech(this, this);

        if (NetworkConnection() == false)
        {
            NotConnected_showAlert();
        }
        checkPermissions();

    }


    @Override
    public void onInit(int status) { // TTS가 init되었을 때 실행되는 함수
        if(TextToSpeech.SUCCESS == status)
        {
            int language = tts.setLanguage(Locale.ENGLISH);
            if(language == TextToSpeech.LANG_MISSING_DATA || language == TextToSpeech.LANG_NOT_SUPPORTED)
            {
                Toast.makeText(getApplicationContext(), "TTS 작업에 실패하였습니다.", Toast.LENGTH_SHORT).show();
            }
            else
            {
                TextView textView = findViewById(R.id.textView);
                String text = textView.getText().toString();
                tts.setPitch((float) 0.1);      // 음량
                tts.setSpeechRate((float) 1.0); // 재생속도
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "TTS 작업에 실패하였습니다.", Toast.LENGTH_SHORT).show();
        }
    }


    private void initView() // 권한이 granted되었을 때 실행되는 함수
    {
        new getGoogle().execute();
    }

    private class getGoogle extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... strings) {


            try {
                Connection.Response response = Jsoup.connect("https://www.google.com").method(Connection.Method.GET).execute();
                Document document = response.parse();
                String result = document.toString();
                return result;

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            textView2.setText(s);
        }
    }

    private boolean NetworkConnection() //네트워크 연결하는 함수
    {
        int[] networkTypes = {ConnectivityManager.TYPE_MOBILE, ConnectivityManager.TYPE_WIFI};
        try {
            ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            for (int networkType : networkTypes) {
                NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
                if (activeNetwork != null && activeNetwork.getType() == networkType) {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    private void NotConnected_showAlert()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("네트워크 연결 오류");
        builder.setMessage("사용 가능한 무선네트워크가 없습니다.\n" + "먼저 무선네트워크 연결상태를 확인해 주세요.")
                .setCancelable(false)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish(); // exit
                        //application 프로세스를 강제 종료
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= 23) { // 마시멜로(안드로이드 6.0) 이상 권한 체크
            TedPermission.with(context)
                    .setPermissionListener(permissionlistener)
                    .setRationaleMessage("앱을 이용하기 위해서는 접근 권한이 필요합니다")
                    .setDeniedMessage("앱에서 요구하는 권한설정이 필요합니다...\n [설정] > [권한] 에서 사용으로 활성화해주세요.")
                    .setPermissions(new String[]{
                            android.Manifest.permission.WRITE_CONTACTS, // 주소록 액세스 권한
                            android.Manifest.permission.READ_EXTERNAL_STORAGE,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE // 기기, 사진, 미디어, 파일 엑세스 권한
                    })
                    .check();

        } else {
            initView();
        }
    }
}