package com.example.ssibal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class Main_SpecificPatientInfo_history_add_choosehospital_adapter extends BaseAdapter {
    private ArrayList<Info_Hospital> listViewItemList = new ArrayList<Info_Hospital>();

    public Main_SpecificPatientInfo_history_add_choosehospital_adapter(){
    }

    @Override
    public int getCount(){
        return listViewItemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.main_specificpatientinfo_history_add_choosehospital_listitem, parent, false);
        }
        TextView hospitalname = (TextView) convertView.findViewById(R.id.hospital_name);
        TextView hospitalphonenumber = (TextView) convertView.findViewById(R.id.hospital_phonenumber);
        TextView hospitaladdress = (TextView) convertView.findViewById(R.id.hospital_address);

        final Info_Hospital listViewItem = listViewItemList.get(position);

        hospitalname.setText(listViewItem.gethospitalname());
        hospitalphonenumber.setText(listViewItem.gethospitalphonenumber());
        hospitaladdress.setText(listViewItem.gethospitaladdress());


        //상세정보버튼 클릭시
        Button specificbutton = (Button)convertView.findViewById(R.id.selectbutton);
        specificbutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //해당 리스트 클릭 시 이벤트
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("병원 선택");
                alertDialogBuilder.setMessage(listViewItem.gethospitalname() + "을 선택하시겠습니까?");
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Intent specificintent = new Intent(context, Main_SpecificPatientInfo_history_add_main.class);
                                specificintent.putExtra("hospitalname", listViewItem.gethospitalname());
                                ((Main_SpecificPatientInfo_history_add_choosehospital)context).startActivity(specificintent);
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });

        return convertView;
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    public Object getItem(int position){
        return listViewItemList.get(position);
    }

    public void addItem(String name, String phonenumber, String address){
        Info_Hospital item = new Info_Hospital();

        item.sethospitalname(name);
        item.sethospitalphonenumber(phonenumber);
        item.sethospitaladdress(address);
        listViewItemList.add(item);
    }
}