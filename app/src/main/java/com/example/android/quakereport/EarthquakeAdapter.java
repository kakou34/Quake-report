package com.example.android.quakereport;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.graphics.drawable.GradientDrawable;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    //public constructor to create an Earthquake adapter
    public EarthquakeAdapter(Context context, ArrayList<Earthquake> words) {
        super(context, 0, words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        //get the earthquake at tha current position
        Earthquake currentEarthquake = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID magnitude.
        TextView magnitude = (TextView) listItemView.findViewById(R.id.magnitude);
        //create a decimalFormatter to write the magnitude with one digit after the floating point
        DecimalFormat formatter = new DecimalFormat("0.0");
        //use the formatter to create the magnitude string
        String mag = formatter.format(currentEarthquake.getMagnitude());
        //set the text of the magnitude text view to show the magnitude string
        magnitude.setText(mag);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        // Find the TextView in the list_item.xml layout with the ID primary_location.
        TextView primaryLocation = (TextView) listItemView.findViewById(R.id.primary_location);
        // Find the TextView in the list_item.xml layout with the ID primary_location.
        TextView locationOffset = (TextView) listItemView.findViewById(R.id.location_offset);

        //get the string containing the details of the place
        String place = currentEarthquake.getPlace();
        if(place.contains(" of ")){
            //get the index of "of"
            int indexOf = place.indexOf("of");
            //set the offset's text to contain the 1st part of the string
            locationOffset.setText(place.substring(0,(indexOf+2)));
            //set the primary location's text to contain the rest of the place string
            primaryLocation.setText(place.substring(indexOf+3));
        }
        else {
            //if there is no offset we write "near to" in the soffset TextView
            locationOffset.setText("Near the");
            //set the text of primary location's TextView to contain the location string
            primaryLocation.setText(place);
        }

        //creating a date object from the long "time"
        Date date = new Date(currentEarthquake.getTime());

        // Find the TextView in the list_item.xml layout with the ID date.
        TextView date_text_view = (TextView) listItemView.findViewById(R.id.date);
        //set the text of "date_text_view" to be the string returned by the method timeFormatter
        date_text_view.setText(dateFormatter(date));

        // Find the TextView in the list_item.xml layout with the ID time.
        TextView time_text_view = (TextView) listItemView.findViewById(R.id.time);
        //set the text of "time_text_view" to be the string returned by the method timeFormatter
        time_text_view.setText(timeFormatter(date));

        // Return the whole list item layout (containing 3 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }

    //helper methods
    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String dateFormatter(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String timeFormatter(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
    //setting the color of the circle according to the magnitude
    private int getMagnitudeColor(double magnitude){
        int magCategory = (int) Math.floor(magnitude);
        int magnitudeColorID;
        switch (magCategory){
            case 0:
            case 1: magnitudeColorID = R.color.magnitude1;break;
            case 2 : magnitudeColorID = R.color.magnitude2;break;
            case 3 : magnitudeColorID = R.color.magnitude3;break;
            case 4 : magnitudeColorID = R.color.magnitude4;break;
            case 5 : magnitudeColorID = R.color.magnitude5;break;
            case 6 : magnitudeColorID = R.color.magnitude6;break;
            case 7 : magnitudeColorID = R.color.magnitude7;break;
            case 8 : magnitudeColorID = R.color.magnitude8;break;
            case 9 : magnitudeColorID = R.color.magnitude9;break;
            default: magnitudeColorID = R.color.magnitude10plus;break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorID);
    }
}
