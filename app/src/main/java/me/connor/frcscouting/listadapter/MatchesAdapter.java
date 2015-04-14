package me.connor.frcscouting.listadapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import me.connor.frcscouting.R;
import me.connor.frcscouting.matches.MItem;
import me.connor.frcscouting.matches.MatchesHeaderItem;
import me.connor.frcscouting.matches.MatchesItem;

public class MatchesAdapter extends ArrayAdapter<MItem>
{
	private MItem objItem;
	private LayoutInflater vi;

	private ViewHolderSectionName holderSection;
	private ViewHolderName holderName;

	public MatchesAdapter(Context context, int resource, List<MItem> items)
	{
		super(context, resource, items);

		vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent)
	{
		objItem = getItem(position);

		if (objItem.isHeader())
		{
			MatchesHeaderItem si = (MatchesHeaderItem) objItem;

			if (convertView == null || !convertView.getTag().equals(holderSection))
			{
				convertView = vi.inflate(R.layout.list_header, null);

				holderSection = new ViewHolderSectionName();
				convertView.setTag(holderSection);
			} else
			{
				holderSection = (ViewHolderSectionName) convertView.getTag();
			}

			holderSection.section = (TextView) convertView.findViewById(R.id.list_header_title);
			holderSection.section.setText(si.getMatchTimeString());

		} else
		{
			MatchesItem matchItem = (MatchesItem) objItem;

			if (convertView == null || !convertView.getTag().equals(holderName))
			{
				convertView = vi.inflate(R.layout.list_item, null);

				holderName = new ViewHolderName();
				convertView.setTag(holderName);
			} else
			{
				holderName = (ViewHolderName) convertView.getTag();
			}

			holderName.name = (TextView) convertView.findViewById(R.id.list_item_title);
			holderName.subtitle = (TextView) convertView.findViewById(R.id.list_item_subtitle);
			holderName.status = (TextView) convertView.findViewById(R.id.list_item_status);

			if (holderName.name != null)
			{
				holderName.name.setText(matchItem.getTeamNickname());
				holderName.subtitle.setText(matchItem.getTeamId() + "");

				holderName.status.setText(matchItem.getStatus().getTitle());
				holderName.status.setTextColor(matchItem.getStatus().getColor());

				if (matchItem.isAlliance())
				{
					convertView.setBackgroundColor(Color.parseColor("#0a048b03"));
				} else
				{
					convertView.setBackgroundColor(Color.parseColor("#0a8b0002"));
				}
			}
		}
		return convertView;
	}

	public static class ViewHolderName
	{
		public TextView name;
		public TextView subtitle;
		public TextView status;
	}

	public static class ViewHolderSectionName
	{
		public TextView section;
	}
}