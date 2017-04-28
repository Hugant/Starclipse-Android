package hugant.starclipse_android;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import hugant.starclipse_android.Infrastructure.InfrastructureAdapter;
import hugant.starclipse_android.building.House;

/**
 * Created by Hugant on 28.04.2017.
 */

public class BuildingActivity extends Activity {
    Building building;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.building);

    }

}
