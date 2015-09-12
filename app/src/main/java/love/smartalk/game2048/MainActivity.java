package love.smartalk.game2048;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.FileNotFoundException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import love.smartalk.game2048.utils.SPData;
import love.smartalk.game2048.utils.ShareUtils;
import love.smartalk.game2048.widget.Love2048Layout;

public class MainActivity extends BaseActivity implements Love2048Layout.OnLove2048Listener {

    @Bind(R.id.tv_score)
    TextView tvScore;
    @Bind(R.id.tv_best_score)
    TextView tvBestScore;
    @Bind(R.id.ll_game_view)
    Love2048Layout llGameView;
    @OnClick(R.id.tv_restart)
    void restart(){
        llGameView.restart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        llGameView.setLove2048Listener(this);
        tvBestScore.setText("BestScore: " + SPData.getBestScore());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_share:
                try {
                    ShareUtils.shareLove2048(this);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.action_about:
                startActivity(new Intent(this,AboutActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onScoreChanged(int score) {
        if (SPData.getBestScore() < score){
            tvBestScore.setText("BestScore: "+ score);
            SPData.saveBestScore(score);
        }

        String scoreStr = "CurrentScore: " + score;
        tvScore.setText(scoreStr);
    }

    @Override
    public void onGameOver() {
        showGameOver();
    }

    private void showGameOver() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Love2048");
        builder.setMessage("Small CaiBi, Game over! Try again? ");
        builder.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                llGameView.restart();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

}
