/**
 Assignment 9
 Student Number : 109504501
 Course : CE1004-B
 */
package com.example.a9_109504501;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("匯率轉換器");

        TextField textField = new TextField("");
        ComboBox<String> recent = new ComboBox();
        recent.getItems().addAll("美元","台幣","日圓","歐元","人民幣");
        Button exchange = new Button("<->");
        ComboBox<String> after = new ComboBox();
        after.getItems().addAll("台幣","美元","日圓","歐元","人民幣");
        Button change = new Button("轉換");
        Text result = new Text("");

        HBox a = new HBox();
        a.getChildren().addAll(recent,exchange,after);
        VBox out = new VBox(10);
        out.setPadding(new Insets(30,30,30,30));
        out.getChildren().addAll(textField,a,change,result);

        Scene sc = new Scene(out,300,300);
        stage.setScene(sc);
        stage.show();

        // 匯率互換button
        exchange.setOnAction(e -> {
            String now_currency = recent.getValue();
            String after_currency = after.getValue();
            recent.setValue(after_currency);
            after.setValue(now_currency);
        });

        // 匯率轉換button
        change.setOnAction(e -> {
            float money = Float.parseFloat(textField.getText());
            float ans = 0;
            String now_currency = recent.getValue();
            String after_currency = after.getValue();

            // 先換成美元
            if(now_currency.equals("台幣")){
                money = (float) ((1/29.42) * money);
            }
            else if(now_currency.equals("日幣")){
                money = (float) ((1/124.819687) * money);
            }
            else if(now_currency.equals("歐元")){
                money = (float) ((1/0.913381) * money);
            }
            else if(now_currency.equals("人民幣")){
                money = (float) ((1/6.347357) * money);
            }

            // 以美元換算其他幣種
            if(after_currency.equals("台幣")){
                ans = (float) (money * 29.42);
            }
            else if(after_currency.equals("日圓")){
                ans = (float) (money * 124.819687);
            }
            else if(after_currency.equals("歐元")){
                ans = (float) (money * 0.913381);
            }
            else if(after_currency.equals("人民幣")){
                ans = (float) (money * 6.347357);
            }
            else if(after_currency.equals("美元")){
                ans = money;
            }
            result.setText(textField.getText() + now_currency + " = " + ans + after_currency);
        });
    }

    public static void main(String[] args) {
        launch();
    }
}