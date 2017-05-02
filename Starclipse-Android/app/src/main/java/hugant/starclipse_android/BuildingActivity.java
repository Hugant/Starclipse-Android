package hugant.starclipse_android;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Hugant on 28.04.2017.
 */

public class BuildingActivity extends Activity {
    Building building;
    TextView name;
    TextView description;
	ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.building);

	    name = (TextView) findViewById(R.id.name);
	    description = (TextView) findViewById(R.id.description);
	    image = (ImageView) findViewById(R.id.imageView);

    }

}
