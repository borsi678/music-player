package db;

import android.content.Context;

import androidx.annotation.Nullable;

public class BaseDeDatosRolas extends BaseDeDatosMusica {

    Context context;

    public BaseDeDatosRolas(@Nullable Context context) {
        super(context);
        this.context=context;
    }
}
