package com.lottodroid;

import java.util.List;
import java.util.zip.DataFormatException;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.lottodroid.communication.LotteryFetcher;
import com.lottodroid.communication.LotteryInfoUnavailableException;
import com.lottodroid.communication.MockLotteryFetcher;
import com.lottodroid.communication.ServerLotteryFetcher;
import com.lottodroid.model.Lottery;

/**
 * Activity for the details screen.
 */
public class DetailsActivity extends ExpandableListActivity {

  /**
   * If the offline mode is true, no communication with the server will be performed: mock data will
   * be generated instead. See the implementations for {@link LotteryFetcher}. 
   * 
   * TODO: Move this flag to the command-line flag list
   */
  private static boolean OFFLINE_MODE = true;

  /** Number of last results displayed by default */
  private static int NUM_RESULTS_SHOW = 4;

  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.details);

    try {
      Bundle extras = getIntent().getExtras();
      if (extras == null) {
        throw new DataFormatException();
      }
      
      // Get the lottery type selected from the main activity
      int idLottery = extras.getInt("lottery");
      
      // Habría que hacer algun tipo de mapping como en viewconfigurer para no hacer
      // switch, o bien guardarlo en un xml ( pero no podríamos hacer los key/value.. )
      // TODO:  pensar buena estructura para manejar icono, titulo, 
      //        controlador/url del servidor, views, content_xml, etc. para cada sorteo
      
      // set the icon and title
      ImageView iconCtrl = (ImageView) findViewById(R.id.icon);
      TextView titleCtrl = (TextView) findViewById(R.id.title);
      
      switch (idLottery) {
      case 0:
        titleCtrl.setText("Bonoloto");
        iconCtrl.setImageResource(R.drawable.bonoloto);
        break;
      case 1:
        titleCtrl.setText("Quiniela");
        iconCtrl.setImageResource(R.drawable.quiniela);    
        break;

      default:
        throw new DataFormatException();
      }
      
      // this line will be placed on the callback of another thread
      setListAdapter(new DetailsViewAdapter(this, fetchDataForDetailsView(idLottery)));

      // Expand the last lottery result
      if (getExpandableListAdapter().getGroupCount() > 0)
        getExpandableListView().expandGroup(0);

    } catch (LotteryInfoUnavailableException e) {
      Log.e("Lottodroid", "Ouch, no data!", e);
    } catch (DataFormatException e) {
      Log.e("Lottodroid", "Errors passing data from main activity", e);
    }
  }

  /**
   * Fetches the data that the details view will display: the last results for a specify lottery
   * type
   */
  private List<? extends Lottery> fetchDataForDetailsView(int idLottery)
      throws LotteryInfoUnavailableException, DataFormatException {
    LotteryFetcher dataFetcher = OFFLINE_MODE ? 
                                  new MockLotteryFetcher()
                                : new ServerLotteryFetcher();
    List<? extends Lottery> listLottery;
    
    switch (idLottery) {
    case 0:
      listLottery = dataFetcher.retrieveLastBonolotos(0, NUM_RESULTS_SHOW);
      break;
    case 1:
      listLottery = dataFetcher.retrieveLastQuinielas(0, NUM_RESULTS_SHOW);
      break;

    default:
      throw new DataFormatException();
    }

    return listLottery;
  }

}
