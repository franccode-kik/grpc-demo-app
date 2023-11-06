package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.drathveloper.grpc.User;
import com.drathveloper.grpc.UserAddress;
import com.drathveloper.grpc.UserBulkLoadRequest;
import com.drathveloper.grpc.UserBulkLoadResponse;
import com.drathveloper.grpc.UserServiceGrpc;
import com.google.protobuf.Timestamp;

import java.util.ArrayList;
import java.util.List;

import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class MainActivity extends AppCompatActivity {

    private static final String HOST = "10.0.2.2";
    private static final int PORT = 50052;

    EditText hostEdit;
    EditText portEdit;
    EditText messageEdit;
    TextView resultText;
    Button submitButton;
    ManagedChannelBuilder mChannel;

    UserServiceGrpc.UserServiceStub userServiceStub;
    UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hostEdit = (EditText) findViewById(R.id.host);
        portEdit = (EditText) findViewById(R.id.port);
        messageEdit = (EditText) findViewById(R.id.message1);

        submitButton = (Button) findViewById(R.id.submit);

        TextView resultText = (TextView) findViewById(R.id.message1);
        //  resultText.setMovementMethod(new ScrollingMovementMethod());

        mChannel = ManagedChannelBuilder.forAddress(HOST, PORT).usePlaintext();

        userServiceStub = UserServiceGrpc.newStub(mChannel.build());
        userServiceBlockingStub = UserServiceGrpc.newBlockingStub(mChannel.build());

        setContentView(R.layout.activity_main);
    }

    public static UserBulkLoadRequest generateBulkLoadGrpcRequest(int numUsers) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < numUsers; i++) {
            users.add(
                    User.newBuilder()
                            .setUsername("someUsername" + Math.random())
                            .setFirstName("name" + Math.random())
                            .setLastName("lastName" + Math.random())
                            .setEmail("email" + Math.random() + "@email.com")
                            .setPhone("+34666" + Math.random())
                            .setBirthDate(Timestamp.newBuilder()
                                    .setSeconds(System.currentTimeMillis()/1000)
                                    .build())
                            .setAddress(UserAddress.newBuilder()
                                    .setCountry("Spain")
                                    .setCity("Madrid")
                                    .setState("Madrid")
                                    .setAddress("Avenida Ciudad de Barcelona 23, 4B")
                                    .setPostalCode("28007")
                                    .build())
                            .build());
        }
        return UserBulkLoadRequest.newBuilder().addAllUsers(users).build();
    }
    


    public void sendUnaryGrpcMessage(View view) {

        // submitButton.setEnabled(false);
        // resultText.setText("");

        UserBulkLoadRequest request = generateBulkLoadGrpcRequest(5);
        StreamObserver streamObserver = new StreamObserver<UserBulkLoadResponse>() {
            @Override
            public void onNext(UserBulkLoadResponse value) {

                Log.println(Log.VERBOSE,"Info",value.getCreatedUsersList().toString());
            }

            @Override
            public void onError(Throwable t) {
                Log.println(Log.ERROR,"Error","Error en la llamada ");
                t.printStackTrace();

            }

            @Override
            public void onCompleted() {
                Log.println(Log.INFO,"Info","Llamada completada ");
            }
        };
        userServiceStub.bulkLoad(request, streamObserver);

    }


}