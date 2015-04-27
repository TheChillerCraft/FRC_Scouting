package me.connor.frcscouting.tabs.team_tab.info_view;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.connor.frcscouting.R;
import me.connor.frcscouting.TabsPagerAdapter;
import me.connor.frcscouting.listadapter.ListItem;
import me.connor.frcscouting.tabs.team_tab.Team;
import me.connor.frcscouting.tabs.team_tab.info_view.tabs.comments_tab.TeamCommentsFragment;
import me.connor.frcscouting.tabs.team_tab.info_view.tabs.info_tab.TeamInfoFragment;

public class TeamInfoActivity extends ActionBarActivity implements ActionBar.TabListener
{
	private Team team;

	private List<ListItem> itemsSections = new ArrayList<>();

	private TabsPagerAdapter mSectionsPagerAdapter;
	private ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team_info);

		Intent callingIntent = getIntent();

		team = callingIntent.getParcelableExtra("teamData");

		setTitle(team.getTeamName());
		getSupportActionBar().setSubtitle(team.getTeamNumber() + "");

		mSectionsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager(), new Fragment[] {
				new TeamInfoFragment(),
				new TeamCommentsFragment()
		});

		mViewPager = (ViewPager) findViewById(R.id.team_info_pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
		{
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
			{

			}

			@Override
			public void onPageSelected(int position)
			{
				getSupportActionBar().setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrollStateChanged(int state)
			{

			}
		});

		getSupportActionBar().setElevation(0);
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		getSupportActionBar().addTab(getSupportActionBar().newTab().setText("Info").setTabListener(this));
		getSupportActionBar().addTab(getSupportActionBar().newTab().setText("Comments").setTabListener(this));
	}

	public Team getTeam()
	{
		return team;
	}

	public List<ListItem> getItemsSections()
	{
		return itemsSections;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu_team_info, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();

		if (id == R.id.action_settings)
		{
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public TextView.OnEditorActionListener textEditDoneEvent = new TextView.OnEditorActionListener()
	{
		@Override
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
		{
			if (actionId == EditorInfo.IME_ACTION_DONE)
			{
				findViewById(R.id.team_info_layout).requestFocus();

				InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				in.hideSoftInputFromWindow(v.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

				return true;
			}

			return false;
		}
	};

	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
	{
		mViewPager.setCurrentItem(tab.getPosition(), true);
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
	{

	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
	{

	}
}