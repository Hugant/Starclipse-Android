package hugant.starclipse_android.building;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import hugant.starclipse_android.R;
import hugant.starclipse_android.common.Resources;
import hugant.starclipse_android.common.Subject;

/**
 * Created by Hugant on 15.05.2017.
 */

public class BuildingWarehouseAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater layoutInflater;
	private ArrayList<Subject> subjects;

	public BuildingWarehouseAdapter(Context context, Resources resources) {
		this.context = context;
		this.subjects = resources.getSubjects();
		this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return subjects.size();
	}

	@Override
	public Subject getItem(int position) {
		return subjects.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;

		if (view == null) {
			view = layoutInflater.inflate(R.layout.warehouse_adapter_item, parent, false);
		}
		final Subject subject = getItem(position);

		((ImageView) view.findViewById(R.id.resourceIcon)).setImageResource(subject.getImage());
		((TextView) view.findViewById(R.id.nameOfResource))
				.setText(subject.getType().substring(0, 1).toUpperCase()
						+ subject.getType().substring(1));
		((TextView) view.findViewById(R.id.values))
				.setText(subject.getValue() +
						(subject.getMaxValue() == null ? "" : " / " + subject.getMaxValue()));
		view.setEnabled(false);
		return view;
	}

}
