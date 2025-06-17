package com.example.test;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.adapter.TexViewAdapter;
import com.example.test.adapter.TexViewAdapter2;
import com.example.test.model.TexItem;
import com.example.test.model.TexItem2;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TaxActivity5 extends AppCompatActivity {

    private RecyclerView recyclerview;
    private TextView tvIncome, tvTax;

    private ArrayList<TexItem2> data = new ArrayList<>();


    String TITLE = "所得项目小类：";
    String CAMPANY = "扣缴义务人：";

    String ALI = "阿里巴巴（北京）软件服务公司";
    String FAW = "一汽（北京）软件科技有限公司";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity5_tax);
        data.add(new TexItem2("2024-05",TITLE + "正常工资薪金" , CAMPANY + FAW, "29146.67", "1587.28"));
        data.add(new TexItem2("2024-04",TITLE + "正常工资薪金" , CAMPANY + FAW, "29146.67", "1587.28"));
        data.add(new TexItem2("2024-03",TITLE + "正常工资薪金" , CAMPANY + FAW, "29146.67", "1587.28"));
        data.add(new TexItem2("2024-02",TITLE + "正常工资薪金" , CAMPANY + FAW, "29146.67", "512.99"));
        data.add(new TexItem2("2024-01",TITLE + "正常工资薪金" , CAMPANY + FAW, "29146.67", "514.44"));
        data.add(new TexItem2("2024-01",TITLE + "全年一次性奖金收入" , CAMPANY + FAW, "29146.67", "504.22"));
        data.add(new TexItem2("2024-01",TITLE + "正常工资薪金" , CAMPANY + FAW, "0", "0"));


        double incomeSum = 0;
        double taxSum = 0;
        for (int i = 0; i < data.size(); i++) {
            incomeSum += Double.parseDouble(data.get(i).getIncome());
            taxSum += Double.parseDouble(data.get(i).getTax());
        }
        tvIncome = findViewById(R.id.tv_income_sum);
        tvTax = findViewById(R.id.tv_tax_sum);
        DecimalFormat df = new DecimalFormat("#.##"); // 两位小数，自动四舍五入
        String formatted = df.format(incomeSum);
        tvIncome.setText(formatted);
        String format = df.format(taxSum);
        tvTax.setText(format);
        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerview.setAdapter(new TexViewAdapter2(data));
        findViewById(R.id.btn_exit).setOnClickListener(view -> {
            finish();
        });
    }
}