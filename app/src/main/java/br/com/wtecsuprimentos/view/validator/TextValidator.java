package br.com.wtecsuprimentos.view.validator;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public abstract class TextValidator implements TextWatcher {

    private final EditText editText;

    public TextValidator(EditText editText){
        this.editText = editText;
    }

    public abstract void validate(EditText editText, String text);

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String text = editText.getText().toString();
        validate(editText, text);
    }
}
