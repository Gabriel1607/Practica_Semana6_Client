package com.example.practica_semana6_client;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;

import com.example.practica_semana6_client.model.Instruction;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    private Button upBtn,downBtn,leftBtn,rightBtn,colorBtn;
    private Socket socketcito;
    private BufferedWriter escritorcito;
    private BufferedReader lectorcito;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        upBtn = findViewById(R.id.upBtn);
        downBtn = findViewById(R.id.downBtn);
        leftBtn = findViewById(R.id.leftBtn);
        rightBtn = findViewById(R.id.rightBtn);
        colorBtn = findViewById(R.id.colorBtn);
        initClient();

        upBtn.setOnTouchListener(
                (view,event)->{
                    Gson gson = new Gson();
                    String key;
                    Boolean isactive;
                    Instruction obj;
                    String json;
                    switch(event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            gson = new Gson();
                             key = "UP";
                            isactive= true;
                            obj = new Instruction(key,isactive);
                            json = gson.toJson(obj);
                            sendMessage(json);
                            break;
                        case MotionEvent.ACTION_UP:
                            gson = new Gson();
                             key = "UP";
                             isactive= false;
                             obj = new Instruction(key,isactive);
                            json = gson.toJson(obj);
                            sendMessage(json);
                            break;
                    }
                   return true;
                }
        );
        downBtn.setOnTouchListener(
                (view,event)->{
                    Gson gson = new Gson();
                    String key;
                    Boolean isactive;
                    Instruction obj;
                    String json;
                    switch(event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            gson = new Gson();
                            key = "DOWN";
                            isactive= true;
                            obj = new Instruction(key,isactive);
                            json = gson.toJson(obj);
                            sendMessage(json);
                            break;
                        case MotionEvent.ACTION_UP:
                            gson = new Gson();
                            key = "DOWN";
                            isactive= false;
                            obj = new Instruction(key,isactive);
                            json = gson.toJson(obj);
                            sendMessage(json);
                            break;
                    }
                    return true;
                }
        );
        leftBtn.setOnTouchListener(
                (view,event)->{
                    Gson gson = new Gson();
                    String key;
                    Boolean isactive;
                    Instruction obj;
                    String json;
                    switch(event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            gson = new Gson();
                            key = "LEFT";
                            isactive= true;
                            obj = new Instruction(key,isactive);
                            json = gson.toJson(obj);
                            sendMessage(json);
                            break;
                        case MotionEvent.ACTION_UP:
                            gson = new Gson();
                            key = "LEFT";
                            isactive= false;
                            obj = new Instruction(key,isactive);
                            json = gson.toJson(obj);
                            sendMessage(json);
                            break;
                    }
                    return true;
                }
        );
        rightBtn.setOnTouchListener(
                (view,event)->{
                    Gson gson = new Gson();
                    String key;
                    Boolean isactive;
                    Instruction obj;
                    String json;
                    switch(event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            gson = new Gson();
                            key = "RIGHT";
                            isactive= true;
                            obj = new Instruction(key,isactive);
                            json = gson.toJson(obj);
                            sendMessage(json);
                            break;
                        case MotionEvent.ACTION_UP:
                            gson = new Gson();
                            key = "RIGHT";
                            isactive= false;
                            obj = new Instruction(key,isactive);
                            json = gson.toJson(obj);
                            sendMessage(json);
                            break;
                    }
                    return true;
                }
        );
        colorBtn.setOnTouchListener(
                (view,event)->{
                    Gson gson = new Gson();
                    String key;
                    Boolean isactive;
                    Instruction obj;
                    String json;
                    switch(event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            gson = new Gson();
                            key = "COLOR";
                            isactive= true;
                            obj = new Instruction(key,isactive);
                            json = gson.toJson(obj);
                            sendMessage(json);
                            break;
                        case MotionEvent.ACTION_UP:
                            gson = new Gson();
                            key = "COLOR";
                            isactive= false;
                            obj = new Instruction(key,isactive);
                            json = gson.toJson(obj);
                            sendMessage(json);
                            break;
                    }
                    return true;
                }
        );
    }

    public void initClient() {
        new Thread(
                ()->{
                    try {
                        //Paso 2: Enviar solicitud de conexion
                        socketcito = new Socket("192.168.1.6",6969);
                        //Paso 3: Cliente y server conectados
                        System.out.println("Se ha conectado al servidor!!!");

                        InputStream is = socketcito.getInputStream();
                        InputStreamReader isr = new InputStreamReader(is);
                        lectorcito = new BufferedReader(isr);

                        OutputStream os = socketcito.getOutputStream();
                        OutputStreamWriter osw = new OutputStreamWriter(os);
                        escritorcito = new BufferedWriter(osw);

                        while(true) {
                            System.out.println("Esperando mensaje....");
                            String line = lectorcito.readLine();
                            System.out.println("Recibido: " + line);


                        }

                    } catch (UnknownHostException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

        ).start();
    }
    public void sendMessage(String msg) {
        new Thread(
                ()->{
                    try {
                        escritorcito.write(msg+"\n");
                        escritorcito.flush();

                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
        ).start();
    }
}