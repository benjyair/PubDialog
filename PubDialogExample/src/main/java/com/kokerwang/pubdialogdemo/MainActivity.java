package com.kokerwang.pubdialogdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.kokerwang.pubdialog.DialogGroup;
import com.kokerwang.pubdialog.DialogObject;
import com.kokerwang.pubdialog.PubDialogFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KokerWang on 15/2/26.
 */
public class MainActivity extends ActionBarActivity implements PubDialogFragment.ItemClickListener {

    private PubDialogFragment pubDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_style_0:
                List<String> list1 = new ArrayList<>(3);
                list1.add("Send message");
                list1.add("Like profile");
                list1.add("Add to favorites");
                pubDialogFragment = PubDialogFragment.newInstance(list1, false);
                break;
            case R.id.btn_style_1:
                List<String> list2 = new ArrayList<>(3);
                list2.add("Update");
                list2.add("Feedback");
                list2.add("EXit");
                DialogGroup group2_1 = new DialogGroup(list2);

                DialogObject cancel = new DialogObject("Cancel");
                cancel.setTextColor(getResources().getColor(android.R.color.white));
                cancel.setBgId(R.drawable.btn_alone);

                DialogGroup group2_2 = new DialogGroup(cancel);
                pubDialogFragment = PubDialogFragment.newInstance(group2_1, group2_2);
                break;
            case R.id.btn_style_2:
                DialogObject qq = new DialogObject("Share to QQ");
                qq.setImgSrc(R.drawable.share_qq_icon);
                DialogObject wechat_friend = new DialogObject("Share to Friend");
                wechat_friend.setImgSrc(R.drawable.share_wechat_friend_icon);
                DialogObject wechat = new DialogObject("Share to Wechat");
                wechat.setImgSrc(R.drawable.share_wechat_cion);
                DialogObject sina = new DialogObject("Share to Sina");
                sina.setImgSrc(R.drawable.share_sina_icon);
                DialogObject sms = new DialogObject("Share to SMS");
                sms.setImgSrc(R.drawable.share_sms_icon);

                DialogGroup group3_1 = new DialogGroup();
                group3_1.add(qq);
                group3_1.add(wechat);
                group3_1.add(wechat_friend);
                group3_1.add(sina);
                group3_1.add(sms);

                DialogGroup group3_2 = new DialogGroup("Cancel");

                pubDialogFragment = PubDialogFragment.newInstance(group3_1, group3_2);
                break;
            case R.id.btn_style_3:
                List<String> list4 = new ArrayList<>(4);
                list4.add("Add apps & widgets");
                list4.add("Home screen settings");
                list4.add("Lock screen settings");
                list4.add("System settings");
                DialogGroup group4_1 = new DialogGroup(list4);

                DialogObject title = new DialogObject("Settings");
                title.setBgId(R.drawable.title_bg);
                title.setTextColor(getResources().getColor(android.R.color.white));
                //add to first as title
                group4_1.add(0, title);

                DialogGroup group4_2 = new DialogGroup("Cancel");

                pubDialogFragment = PubDialogFragment.newInstance(group4_1, group4_2);

                break;
            default:
                break;
        }

        if (pubDialogFragment != null && !pubDialogFragment.isAdded()) {
            pubDialogFragment.setItemClickListener(this);
            pubDialogFragment.show(getSupportFragmentManager(), "setting");
        }
    }

    @Override
    public void onItemClick(View clickedView, DialogObject dialogObject, int groupIndex, int itemIndex) {
        Toast.makeText(this, dialogObject.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && event.getAction() == KeyEvent.ACTION_DOWN) {
            List<String> list1 = new ArrayList<>(3);
            list1.add("Go Blog");
            list1.add("Go Github");
            pubDialogFragment = PubDialogFragment.newInstance(list1, false);
            pubDialogFragment.setItemClickListener(new PubDialogFragment.ItemClickListener() {
                @Override
                public void onItemClick(View clickedView, DialogObject dialogObject, int groupIndex, int itemIndex) {
                    Intent intent;
                    if (itemIndex == 1) {
                        Uri uri = Uri.parse("https://github.com/KokerWang/PubDialog");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                    } else {
                        Uri uri = Uri.parse("http://www.kokerwang.com");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                    }
                    startActivity(intent);
                }
            });
            pubDialogFragment.show(getSupportFragmentManager(), "about");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
