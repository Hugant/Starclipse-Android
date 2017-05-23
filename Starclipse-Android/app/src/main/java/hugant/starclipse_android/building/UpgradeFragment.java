package hugant.starclipse_android.building;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import hugant.starclipse_android.R;
import hugant.starclipse_android.common.Resources;
import hugant.starclipse_android.common.Subject;

/**
 *
 * Created by Hugant on 20.05.2017.
 */

public class UpgradeFragment extends Fragment {

	private Building building;
	private Resources resources;

	public UpgradeFragment(Building building, Resources resources) {
		this.building = building;
		this.resources = resources;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_building_upgrade, container, false);

		((TextView) view.findViewById(R.id.buildingName)).setText(building.getName());
		return view;
	}
}
