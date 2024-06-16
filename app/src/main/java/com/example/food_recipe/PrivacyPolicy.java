package com.example.food_recipe;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PrivacyPolicy extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacy_policy);
        TextView pp = findViewById(R.id.pp);
        TextView info = findViewById(R.id.info);
        TextView use = findViewById(R.id.use);
        TextView dis = findViewById(R.id.dis);
        String privacy= "Welcome to our Food Recipe Application (\"we\", \"our\", \"us\")! We are committed to protecting your privacy. This Privacy Policy explains how we collect, use, disclose, and safeguard your information when you use our mobile application and website (collectively, the \"Application\"). Please read this privacy policy carefully. If you do not agree with the terms of this privacy policy, please do not access the application.";
        String info_we_collect = "We may collect personal information that you voluntarily provide to us when you register with the Application, such as your name, email address, and profile picture. The information we collect may include but is not limited to:\n" +
                "\n" +
                "Personal Data: Name, email address, profile picture, and any other information you voluntarily provide.\n" +
                "Usage Data: Information about your interaction with the Application, such as recipes viewed, searches performed, and preferences.\n" +
                "Device Information: Information about your mobile device, including device type, operating system, and mobile network information.";
        String use_of_info = "We may collect personal information that you voluntarily provide to us when you register with the Application, such as your name, email address, and profile picture. The information we collect may include but is not limited to:\n" +
                "\n" +
                "Personal Data: Name, email address, profile picture, and any other information you voluntarily provide.\n" +
                "Usage Data: Information about your interaction with the Application, such as recipes viewed, searches performed, and preferences.\n" +
                "Device Information: Information about your mobile device, including device type, operating system, and mobile network information.";
        String disclosure_of_info = "We may share your information with third parties only in the following circumstances:\n" +
                "\n" +
                "With your consent.\n" +
                "To comply with legal obligations or respond to lawful requests from governmental authorities.\n" +
                "To protect and defend our rights or property.\n" +
                "In connection with a merger, acquisition, or sale of all or a portion of our assets.";
        String security_of_your_info = "We use administrative, technical, and physical security measures to protect the confidentiality and integrity of your personal information. However, no method of transmission over the Internet or electronic storage is completely secure, and we cannot guarantee absolute security.";
        String contact = "If you have any questions about this Privacy Policy or our practices, please contact us at [ifoodrecipe31@gmail.co].";
    pp.setText(privacy);
    use.setText(use_of_info);
    info.setText(info_we_collect);
    dis.setText(disclosure_of_info);
    }
}
