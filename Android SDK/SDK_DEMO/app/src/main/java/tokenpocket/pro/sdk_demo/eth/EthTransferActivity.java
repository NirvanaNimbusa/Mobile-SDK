package tokenpocket.pro.sdk_demo.eth;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tokenpocket.opensdk.base.TPListener;
import com.tokenpocket.opensdk.base.TPManager;
import com.tokenpocket.opensdk.simple.model.Transfer;

import tokenpocket.pro.sdk_demo.R;

/**
 * Created by duke on 2019/9/18.
 */

public class EthTransferActivity extends Activity implements View.OnClickListener{

    private EditText etFrom;
    private EditText etTo;
    private EditText etAmount;
    private EditText etContract;
    private EditText etSymbol;
    private Button btnTransfer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eth_transfer);

        etFrom = findViewById(R.id.et_from);
        etTo = findViewById(R.id.et_to);
        etAmount = findViewById(R.id.et_amount);
        etContract = findViewById(R.id.et_contract);
        etSymbol = findViewById(R.id.et_symbol);
        btnTransfer = findViewById(R.id.btn_transfer);

        btnTransfer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_transfer:
                transfer();
                break;
        }
    }


    private void transfer() {
        Transfer transfer = new Transfer();
        transfer.setBlockchain("ETH");
        transfer.setProtocol("TokenPocket");
        transfer.setVersion("1.0");
        transfer.setDappName("Test demo");
        transfer.setDappIcon("https://eosknights.io/img/icon.png");
        transfer.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
        transfer.setMemo("demo");
        transfer.setAction("transfer");
        transfer.setFrom(etFrom.getText().toString());
        transfer.setTo(etTo.getText().toString());
        transfer.setContract(etContract.getText().toString());
        transfer.setAmount(Double.parseDouble(etAmount.getText().toString()));
        transfer.setSymbol(etSymbol.getText().toString());
        transfer.setDesc("");
        TPManager.getInstance().transfer(this, transfer, new TPListener() {
            @Override
            public void onSuccess(String s) {
                Toast.makeText(EthTransferActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String s) {
                Toast.makeText(EthTransferActivity.this, s, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancel(String s) {
                Toast.makeText(EthTransferActivity.this, s, Toast.LENGTH_LONG).show();

            }
        });
    }
}
