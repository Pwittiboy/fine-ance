package fineance.framework.screens.components;

import java.util.List;

import fineance.libraries.entities.Statement;
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
		
	}
	
}
