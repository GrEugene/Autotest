package com.example.gcardi.autotest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class TestsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "TestActivity";

    private Test[] questions = {
            new Test(R.string.question01, null, Arrays.asList(R.string.q01_a01, R.string.q01_a02, R.string.q01_a03), R.string.q01_ra),
            new Test(R.string.question02, R.mipmap.test_02, Arrays.asList(R.string.q02_a01, R.string.q02_a02, R.string.q02_a03), R.string.q02_ra),
            new Test(R.string.question03, null, Arrays.asList(R.string.q03_a01, R.string.q03_a02, R.string.q03_a03), R.string.q03_ra),
            new Test(R.string.question04, R.mipmap.test_04, Arrays.asList(R.string.q04_a01, R.string.q04_a02), R.string.q04_ra),
            new Test(R.string.question05, null, Arrays.asList(R.string.q05_a01, R.string.q05_a02), R.string.q05_ra),
            new Test(R.string.question06, R.mipmap.test_06, Arrays.asList(R.string.q06_a01, R.string.q06_a02, R.string.q06_a03), R.string.q06_ra),
            new Test(R.string.question07, null, Arrays.asList(R.string.q07_a01, R.string.q07_a02, R.string.q07_a03), R.string.q07_ra),
            new Test(R.string.question08, R.mipmap.test_08, Arrays.asList(R.string.q08_a01, R.string.q08_a02, R.string.q08_a03, R.string.q08_a04, R.string.q08_a05), R.string.q08_ra),
            new Test(R.string.question09, null, Arrays.asList(R.string.q09_a01, R.string.q09_a02), R.string.q09_ra),
            new Test(R.string.question10, null, Arrays.asList(R.string.q10_a01, R.string.q10_a02, R.string.q10_a03), R.string.q10_ra)
    };

    private LinearLayout layout;
    TestState state                     = new TestState();
    private int index                   = 0;
    private int mistakes                = 0;
    private List<RadioButton> rbList    = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);

        layout = findViewById(R.id.testLayout);
        addView(layout);
        Log.d(TAG, "Create");
    }

    private void addView(LinearLayout layout) {

        if (mistakes < 2) {

            // Создаем вопрос в TextView
            TextView textView = createTextView();

            // Создаем ImageView если он есть
            ImageView imageView = createImageView();

            // Создаем RadioGroup and RadioButtons
            RadioGroup rGroup = createRadio();

            // Создаем кнопку Next
            Button next = createNext();

            // Создаем панель ответов
            LinearLayout answerPanel = createPanelWithAnswers();


            // Добавляем все views на главный layout
            layout.addView(answerPanel);
            layout.addView(textView);
            if (imageView != null) layout.addView(imageView);
            layout.addView(rGroup);
            layout.addView(next);

        } else {

            layout.addView(createPanelWithAnswers());

            TextView failTest = createFinishTextView(R.string.failTest);
            layout.addView(failTest);
        }
    }

    /**
     * Данный метод генерирует TextView
     * @param text
     * @return
     */
    private TextView createFinishTextView(int text) {
        TextView tv = new TextView(TestsActivity.this);
        tv.setText(text);
        tv.setTextSize(25);
        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tv.setTextColor(Color.RED);
        tv.setTop(50);

        return tv;
    }

    /**
     * Данный метод возвращает TextView с вопросом
     * @return
     */
    private TextView createTextView() {
        TextView textView = new TextView(TestsActivity.this);
        textView.setText(questions[index].getQuestion());
        textView.setTextSize(25);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        return textView;
    }

    /**
     * Данный метод возвращает ImageView, если для текущего вопроса есть картинка
     * @return
     */
    private ImageView createImageView() {
        ImageView imageView = null;
        if (questions[index].getImage_id() != null) {
            imageView = new ImageView(TestsActivity.this);
            imageView.setImageResource(questions[index].getImage_id());
            imageView.setTop(10);
        }
        return imageView;
    }

    /**
     * Данный метод возвращает RadioGroup с RadioButtons
     * @return
     */
    private RadioGroup createRadio() {
        RadioGroup rGroup   = new RadioGroup(TestsActivity.this);
        List<Integer> list  = questions[index].getAnswers();

        rGroup.setTop(20);
        rGroup.setGravity(Gravity.LEFT);

        for (int i = 0; i < questions[index].getAnswers().size(); i++) {
            rGroup.addView(new RadioButton(TestsActivity.this));

            RadioButton rBtn = (RadioButton) rGroup.getChildAt(i);
            rBtn.setText(list.get(i));
            rBtn.setTextSize(20);
            rbList.add(rBtn);
            ViewGroup.MarginLayoutParams mp = (ViewGroup.MarginLayoutParams) rBtn.getLayoutParams();
            mp.setMargins(25, 10, 0, 0);
        }
        return rGroup;
    }

    /**
     * Данный метод возвращает кнопку Next
     * @return
     */
    private Button createNext() {
        Button next = new Button(TestsActivity.this);

        next.setId(R.id.next);
        next.setText(R.string.next);
        next.setLayoutParams(new LayoutParams(MATCH_PARENT, WRAP_CONTENT));
        next.setOnClickListener(this);

        return next;
    }

    /**
     * Данный метод отслеживает нажатия на кнопки
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next :
                checkNextButton();
                break;
        }
    }

    /**
     * Данный метод проверяет положение index, mistakes и генерирует views
     */
    private void checkNextButton() {
        if (index < 9) {
            checkAnswers(layout);
            index++;
            layout.removeAllViews();
            addView(layout);

        } else {
            checkAnswers(layout);
            layout.removeAllViews();
            layout.addView(createPanelWithAnswers());

            if (mistakes < 2)
                layout.addView(createFinishTextView(R.string.finishTest));
            else
                layout.addView(createFinishTextView(R.string.failTest));
        }
    }

    /**
     * Данный метод создает панель ответов с пройденными вопросами и их результаты
     * @return
     */
    private LinearLayout createPanelWithAnswers() {
        LinearLayout panel = new LinearLayout(TestsActivity.this);
        panel.setLayoutParams(new LayoutParams(MATCH_PARENT, WRAP_CONTENT));
        panel.setOrientation(LinearLayout.HORIZONTAL);
        panel.setTop(20);
        panel.setGravity(Gravity.CENTER);

        for (int i = 1; i < 11; i++) {
            Button btn = new Button(TestsActivity.this);
            btn.setText("" + i);
            btn.setLayoutParams(new LayoutParams(115, WRAP_CONTENT));
            btn.setEnabled(false);

            if (state.getState(i-1) == 1)
                btn.setBackgroundColor(Color.RED);
            else if (state.getState(i-1) == 2)
                btn.setBackgroundColor(Color.GREEN);

            panel.addView(btn);
        }
        return panel;
    }

    /**
     * Даннный метод обрабатывает ответы и записывает их в state
     * @param layout
     */
    private void checkAnswers(LinearLayout layout) {
        RadioGroup group = null;

        if (questions[index].getImage_id() == null)
            group = (RadioGroup) layout.getChildAt(2);
        else
            group = (RadioGroup) layout.getChildAt(3);

        for (int i = 0; i < rbList.size(); i++) {
            if(rbList.get(i).getId() == group.getCheckedRadioButtonId()) {
                if (rbList.get(i).getText().equals(getString(questions[index].getRightAnswer()))) {
                    state.setStateGreen(index);
                    Toast.makeText(TestsActivity.this, "You are Right", Toast.LENGTH_SHORT).show();
                } else {
                    mistakes++;
                    state.setStateRed(index);
                }
            }
        }

        if (group.getCheckedRadioButtonId() == -1) {
            mistakes++;
            state.setStateRed(index);
        }
    }
}
