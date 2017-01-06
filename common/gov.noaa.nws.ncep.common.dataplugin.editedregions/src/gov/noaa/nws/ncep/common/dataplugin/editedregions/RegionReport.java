package gov.noaa.nws.ncep.common.dataplugin.editedregions;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import com.raytheon.uf.common.dataplugin.persist.PersistablePluginDataObject;

public class RegionReport extends PersistablePluginDataObject {

    private LocalDate date;

    private LocalTime obstime;

    private String obs;

    private String q;

    private String zeroZLocation;

    private String reportLocation;

    private String lo;

    private String ll;

    private String area;

    private int numSpots;

    private String spotClass;

    private String magClass;

    private String magStr;

    private Set<Region> regions;

    @Override
    public String getPluginName() {
        // TODO: Use constant.
        return "editedregions";
    }

}
