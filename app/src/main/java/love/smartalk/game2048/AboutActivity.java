package love.smartalk.game2048;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import love.smartalk.game2048.utils.ShareUtils;
import love.smartalk.game2048.widget.SquareImageView;

public class AboutActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.tv_version)
    TextView tvVersion;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;

    @OnClick(R.id.fab_like)
    void likeClicked() {
        showAlertDialog();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        initViews();
    }

    private void showAlertDialog(){
        SquareImageView imageView = new SquareImageView(this);
        imageView.setImageResource(R.drawable.weixin);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("求打赏");
        builder.setView(imageView);
        builder.setPositiveButton("保存", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                ShareUtils.saveWeChatPay(AboutActivity.this);
                Snackbar.make(mainContent,R.string.image_saved, Snackbar.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
    private void initViews() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbar.setTitle("Love2048");
        setUpVersionName();
    }

    private void setUpVersionName() {
        tvVersion.setText("Version " + BuildConfig.VERSION_NAME);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
