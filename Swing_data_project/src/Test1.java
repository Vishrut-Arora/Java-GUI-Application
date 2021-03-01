import javax.swing.*;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;

import java.util.Date;


public class Test1 {//Main class
    public static void main(String args[])
    {
        swing_frame obj =new swing_frame();
        //MakeTable obj2=new MakeTable();
    }
}

class swing_frame extends JFrame implements ActionListener
{
    JTextField jTextField1;
    JTextField jTextField2;
    JButton jButton1;
    JCheckBox jCheckBox1;
    JCheckBox jCheckBox2;
    JCheckBox jCheckBox3;
    JCheckBox jCheckBox4;
    swing_frame() {
        JLabel jlabel1 = new JLabel();
        jlabel1.setText("DATE: ");
        jTextField1 = new JTextField(1);
        jTextField2 = new JTextField(1);
        JLabel jlabel2 = new JLabel("TOWER A");
        JLabel jlabel3 = new JLabel("TOWER B");
        JLabel jlabel4 = new JLabel("TOWER C");
        JLabel jlabel5 = new JLabel("TOWER D");
        setTitle("Covid-19 Test Data");
        //default layout is card layout -others flow,border,grid;
        GridLayout glt = new GridLayout(25, 1);
        setLayout(glt);

        // jlabel2.setSize();
        //jlabel2.setText("DATE: ");
        add(jlabel1);


        add(jTextField1);
        jCheckBox1 = new JCheckBox();
        jCheckBox2 = new JCheckBox();
        jCheckBox3 = new JCheckBox();
        jCheckBox4 = new JCheckBox();
        jButton1 = new JButton("GET RESULTS");


//        jButton1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String s=jTextField1.getText();
//
//            }
//        });
        jButton1.addActionListener(this);
        //jCheckBox1.addItemListener(this);
        //jCheckBox2.addItemListener(this);
        add(jlabel2);
        add(jCheckBox1);
        add(jlabel3);
        add(jCheckBox2);
        add(jlabel4);
        add(jCheckBox3);
        add(jlabel5);
        add(jCheckBox4);
        add(jButton1);
        add(jTextField2);
        setSize(1000, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==jButton1){

            MakeTable obj=new MakeTable();
            int arr[]=new int[4];//0-A, B-1, C-2,D-3
            jTextField2.setText("Report Generated");
            if(jCheckBox1.isSelected()){
                try {

                    obj.tower1(jTextField1.getText(),"A",0);
                    arr[0]=1;
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            }
            if(jCheckBox2.isSelected()){
                //MakeTable obj=new MakeTable();
                try {
                    obj.tower1(jTextField1.getText(),"B",1);
                    arr[1]=1;
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            }
            if(jCheckBox3.isSelected()){
                //MakeTable obj=new MakeTable();
                try {
                    obj.tower1(jTextField1.getText(),"C",2);
                    arr[2]=1;
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            }
            if(jCheckBox4.isSelected()){
                //MakeTable obj=new MakeTable();
                try {
                    obj.tower1(jTextField1.getText(),"D",3);
                    arr[3]=1;
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            }
            obj.print_table();
            obj.print_table2(arr);
            arr[0]=0;
            arr[1]=0;
            arr[2]=0;
            arr[3]=0;


    }
}
}
class MakeTable{
    // frame
    JFrame f; //Main frame of data
    // Table
    JTable j; //Main frame of column
    String column2[]={" ","Recovered","Active"};
    int arr_recovered[]=new int[4];
    int arr_active[]=new int[4];
    int total_active=0;
    int total_recovered=0;
    int flag=0;

    String[][] s2;// for current details
    String[][] data;
    int k1=0;
    String[] column1 = { "Patient Name", "Age", "Tower","Date of Reporting","Date of Recovery" };
    // Constructor
    MakeTable()
    {
        s2 = new String[20][5];
        // Frame initiallization
        f = new JFrame();

        // Frame Title
        f.setTitle("COVID DETAILS");
        // Data to be displayed in the JTable
        data = new String[][]{
                {"Flora", "6", "A", "01/04/2020"},
                {"Denys", "24", "B", "01/04/2020"},
                {"Jim", "42", "C", "18/05/2020"},
                {"Hazel", "87", "D", "23/06/2020"},
                {"Caery", "72", "A", "01/06/2020"},
                {"David", "7", "B", "14/06/2020"},
                {"Kevim", "37", "D", "05/06/2020"},
                {"Tom", "67", "D", "20/06/2020"},
                {"Bob", "74", "A", "04/07/2020"},
                {"Rachel", "48", "C", "24/07/2020"},
                {"Thomas", "21", "C", "11/06/2020"},
                {"Mary", "17", "D", "21/06/2020"},
                {"Smith", "89", "A", "07/08/2020"},
                {"Pearson", "47", "B", "04/06/2020"},
                {"Anderson", "62", "B", "27/07/2020"},
                {"Johnson", "10", "D", "01/08/2020"},
                {"Robertz", "50", "A", "09/08/2020"},
                {"Julie", "86", "B", "02/05/2020"},
                {"Edith", "42", "D", "07/06/2020"},
                {"John", "95", "D", "01/06/2020"}

        };

        // Column Names
        String[] columnNames = { "Patient Name", "Age", "Tower","Date of Reporting"};

        // Initializing the JTable
        j = new JTable(data, columnNames);
        j.setBounds(100, 100, 1000, 1000);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        f.add(sp);
        // Frame Size
        f.setSize(1000, 200);
        // Frame Visible = true
        f.setVisible(true);
    }

    void tower1(String d1,String str,int index) throws ParseException {
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(d1);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        for (int i = 0; i < 20; i++) {
            Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(data[i][3]);
            Calendar c = Calendar.getInstance();
            c.setTime(date2);
            c.add(Calendar.DATE, 21);
            Date date_after = c.getTime();
            boolean check = (date1.compareTo(date_after) >= 0);
            if (date1.compareTo(date2) >= 0 && data[i][2].equals(str)) {
                for (int j = 0; j < 5; j++)
                    if (j == 4) {
                        if (check) {
                            s2[k1][j] = formatter.format(date_after) + "";
                            ++arr_recovered[index];
                        } else {
                            s2[k1][j] = "Not recovered yet";
                            ++arr_active[index];
                        }
                    } else {
                        s2[k1][j] = data[i][j];
                    }
                ++k1;

            }
            if(date1.compareTo(date2) >= 0 && flag==0){
                if (check) {

                    ++total_recovered;
                } else {

                    ++total_active;
                }
            }

        }
        flag=1;

    }

    void print_table(){
        JFrame  jf1=new JFrame();

      JTable  jt1 = new JTable(s2, column1);
        jt1.setBounds(100, 100, 1000, 1000);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(jt1);
        jf1.add(sp);
        // Frame Size
        jf1.setSize(1000, 400);
        // Frame Visible = true
        jf1.setVisible(true);

    }
    void print_table2(int arr[]){
        String str_new[][]=new String[arr[0]+arr[1]+arr[2]+arr[3]+1][3];
        int index=0;

        if(arr[0]==1){
            str_new[index][0]="TOWER A";
            str_new[index][1]=arr_recovered[0]+"";
            str_new[index][2]=arr_active[0]+"";
            ++index;
        }
        if(arr[1]==1){
            str_new[index][0]="TOWER B";
            str_new[index][1]=arr_recovered[1]+"";
            str_new[index][2]=arr_active[1]+"";
            ++index;
        }
        if(arr[2]==1){
            str_new[index][0]="TOWER C";
            str_new[index][1]=arr_recovered[2]+"";
            str_new[index][2]=arr_active[2]+"";
            ++index;
        }
        if(arr[3]==1){
            str_new[index][0]="TOWER D";
            str_new[index][1]=arr_recovered[3]+"";
            str_new[index][2]=arr_active[3]+"";
            ++index;
        }
        str_new[index][0]="TOTAL UPTO THE DATE (across all towers)";
        str_new[index][1]=total_recovered+"";
        str_new[index][2]=total_active+"";
        JFrame  jf2=new JFrame();

        JTable  jt2 = new JTable(str_new, column2);
        jt2.setBounds(100, 100, 1000, 1000);
        jt2.setGridColor(Color.yellow);
        // adding it to JScrollPane
        JScrollPane sp2 = new JScrollPane(jt2);
        jf2.add(sp2);
        // Frame Size
        jf2.setSize(1000, 400);
        // Frame Visible = true
        jf2.setVisible(true);
    }
}
