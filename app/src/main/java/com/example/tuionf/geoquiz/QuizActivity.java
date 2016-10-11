package com.example.tuionf.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueBtn;
    private Button mFalseBtn;
    private Button mNextBtn;
    private TextView mQuestionTextView;

    private Question[] mQuestionsBank = new Question[]{
            new Question(R.string.question_liaoning,true),
            new Question(R.string.question_shan,false),
            new Question(R.string.question_shanxi,true),
            new Question(R.string.question_water,false),
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mTrueBtn = (Button) findViewById(R.id.btnTrue);
        mFalseBtn = (Button) findViewById(R.id.btnFalse);
        mNextBtn = (Button) findViewById(R.id.btnNext);
        mQuestionTextView = (TextView) findViewById(R.id.tv);

        updateQuestion();

        mTrueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer(true);
            }
        });

        mFalseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionsBank.length;
                updateQuestion();
            }
        });


    }

    private void updateQuestion(){
        int question = mQuestionsBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userAnswer){
        boolean answer = mQuestionsBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        if (answer == userAnswer) {
            messageResId = R.string.corrent_toast;
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionsBank.length;
            updateQuestion();
        }else {
            messageResId = R.string.incorrent_toast;
        }

        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
    }
}
