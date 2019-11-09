package appsoft.com.restapitest;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MActivity extends AppCompatActivity implements View.OnClickListener{

    private TextInputEditText name, password, type;
    private Button get, post;
    private TextView received_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m);
        initialiseData();
    }

    private void initialiseData() {
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        type = findViewById(R.id.type);
        post = findViewById(R.id.post);
        get = findViewById(R.id.get);
        received_data = findViewById(R.id.getData);
        post.setOnClickListener(this);
        get.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.post){
            if(name.getText().toString().trim().isEmpty()){
                name.setError("Should not empty");
            }
            else if(password.getText().toString().trim().isEmpty()){
                password.setError("Should not empty");
            }
            else if(type.getText().toString().trim().isEmpty()){
                type.setError("Should not empty");
            }else{
                // Send Data
                String str_name = name.getText().toString().trim();
                String str_pass = password.getText().toString().trim();
                String str_type = type.getText().toString().trim();

                // convert to json
                //Gson gson = new Gson();
                //String json_str = gson.toJson(new Model(str_name, str_pass, str_type));
                ApiInterface apiInterface = ClientApi.getRetrofit().create(ApiInterface.class);
                Call<String> call = apiInterface.postData(str_name, str_pass, str_type);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String res = response.body();
                        Toast.makeText(MActivity.this, "Response: "+res, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(MActivity.this, "Failure: "+t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
    }
    if(v.getId() == R.id.get){
        ApiInterface apiInterface = ClientApi.getRetrofit().create(ApiInterface.class);
        Call<List<Model>> call = apiInterface.getData();
        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                List<Model> list = response.body();
                Iterator<Model> it = list.iterator();
                StringBuilder builder = new StringBuilder();
                while (it.hasNext()){
                    builder.append("\n");
                    builder.append(it.next().getName());
                }
                received_data.setTextSize(16);
                received_data.setText(builder.toString());
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Toast.makeText(MActivity.this, "Failure: "+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
}
