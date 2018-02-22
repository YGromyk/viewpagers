package com.fepeprog.viewpagers;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by fepeprog on 2/20/18.
 */

public class FragmentPage extends Fragment {
    private String fragmentName;

    private int REQUST_PERMISSION_CODE_CALL = 1;
    private int REQUST_PERMISSION_CODE_SMS = 2;

    public FragmentPage(){
        this.fragmentName = fragmentName;
    }

    public static FragmentPage newInstance(String fragmentName) {
        FragmentPage fragmentPage = new FragmentPage();
        fragmentPage.fragmentName = fragmentName;
        return fragmentPage;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        TextView header = (TextView)view.findViewById(R.id.text_view);
        header.setText(fragmentName);
        Button button = (Button)view.findViewById(R.id.call);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE)
                        == PackageManager.PERMISSION_GRANTED) {
                    String number = new String("tel:" + "380685144255");
                    Intent call = new Intent(Intent.ACTION_CALL, Uri.parse(number));
                    try {
                        Log.d("GRAGMENT", "onClick: ");
                        getActivity().startActivity(call);
                    } catch (SecurityException e) {
                    }
                }else{
                    requestCallPermission();
                }
            }
        });
        Button sms = (Button)view.findViewById(R.id.sms);
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.SEND_SMS) ==
                        PackageManager.PERMISSION_GRANTED) {
                    String sms = new String("smsto:" + "+380685144255");
                    Intent call = new Intent(Intent.ACTION_SENDTO, Uri.parse(sms));
                    call.putExtra("sms_body", "sooome text");
                    Log.d("GRAGMENT", "onClick: ");
                    getActivity().startActivity(call);
                } else{
                    requestSMSPermission();
                }
        }});

        return view;
    }

    public void requestCallPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CALL_PHONE)){
            new AlertDialog.Builder(getActivity())
                    .setTitle("We need a call permission!")
                    .setMessage("Give it us please")
                    .setIcon(R.drawable.ic_notifications_black_24dp)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CALL_PHONE},
                                    REQUST_PERMISSION_CODE_CALL);
                        }
                    })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create()
                    .show();
        }else{
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUST_PERMISSION_CODE_CALL);
        }
    }

    public void requestSMSPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.SEND_SMS)){
            new AlertDialog.Builder(getActivity())
                    .setTitle("We need a call permission!")
                    .setMessage("Give it us please")
                    .setIcon(R.drawable.ic_notifications_black_24dp)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.SEND_SMS},
                                    REQUST_PERMISSION_CODE_SMS);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create()
                    .show();
        }else{
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.SEND_SMS},
                    REQUST_PERMISSION_CODE_SMS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUST_PERMISSION_CODE_CALL){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getActivity(), "Permission call granted!", Toast.LENGTH_SHORT).show();
            }
        }
        if(requestCode == REQUST_PERMISSION_CODE_SMS){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getActivity(), "Permission sms granted!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public String getTitle(){
        return fragmentName;
    }
}
