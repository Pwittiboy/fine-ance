package fineance.framework.screens.components;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import fineance.libraries.entities.Statement;
import fineance.utils.DateFormatter;
import javafx.application.Platform;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import services.impl.ItemServiceImpl;

public class BalanceChart {
	
	// Area chart items
	private AreaChart<String, Number> areaChart;
	private DatePicker dpFrom;
	private DatePicker dpTo;
	private XYChart.Series series;
	
	private List<Statement> statements;
	
	private Date date, startDate, endDate;
	
	// Filter dates
	boolean beforeStart;
	boolean afterEnd;
	
	public BalanceChart(AreaChart<String, Number> areaChart) {
		this.areaChart = areaChart;
	}
	
	public BalanceChart(AreaChart<String, Number> areaChart, DatePicker dpFrom, DatePicker dpTo) {
		// TODO
	}
	
	public void populate() {
		areaChart.getData().clear();
		series = new XYChart.Series<>();
		
		statements = ItemServiceImpl.getStatements();
		Collections.sort(statements, (a,b) -> 
			DateFormatter.formatEpochToDate(a.getDate()).before(DateFormatter.formatEpochToDate(b.getDate())) ? -1 :
				DateFormatter.formatEpochToDate(a.getDate()).equals(DateFormatter.formatEpochToDate(b.getDate())) ? 0 : 1 );
		
		endDate = DateFormatter.formatEpochToDate(statements.get(statements.size()-1).getDate());
		startDate = DateFormatter.formatLocalDateToDate(DateFormatter.formatDateToLocalDate(endDate).minusDays(28));
		
		for (Statement s : statements) {
			date = DateFormatter.formatEpochToDate(s.getDate());
			
			beforeStart = date.before(startDate);
			afterEnd = date.after(endDate);
			
			if (beforeStart || afterEnd) {
				continue;
			}
			series.getData().add(new XYChart.Data<>(DateFormatter.formatEpochToString(s.getDate()), s.getBalance()));
		}
		
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				areaChart.getData().add(series);
			}
		});
	}
	
}
