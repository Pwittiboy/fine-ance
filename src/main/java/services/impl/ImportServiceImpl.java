package services.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import fineance.framework.screens.controllers.ImportingController;
import fineance.libraries.dataaccess.dao.impl.AccountDAOImpl;
import fineance.libraries.dataaccess.dao.impl.StatementDAOImpl;
import fineance.libraries.dataaccess.hibernate.HibernateUtil;
import fineance.libraries.entities.Account;
import fineance.libraries.entities.Statement;
import fineance.utils.CSVUtils;
import fineance.utils.DateFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

public class ImportServiceImpl {
	
	private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private static final Logger LOGGER = Logger.getLogger(ImportServiceImpl.class);
	
	public static final String NATWEST = "Natwest";
	public static final String VIRGIN = "Virgin";
	
	private AccountDAOImpl adao = ItemServiceImpl.getAccountDAO();
	private StatementDAOImpl sdao = ItemServiceImpl.getStatementDAO();
	
	private ImportingController controller;
	
	private Account account;
	private String accountName;
	private String accountNumber;

	private Statement statement;
	private ObservableList<Statement> data = FXCollections.observableArrayList();
	
	private int transactionsImported;
	private int existingTransactionsSkipped;
	private double duration;
	private int transactionsImportedPerSecond;
	private boolean importStatus;
	
	public void importAccount(Account account) {
		adao.updateAccount(account);
	}
	
	public void readCSVNatwest(File file) throws FileNotFoundException {
		this.controller = controller;
		
		Scanner scanner = new Scanner(file);
		int lineNumber = 0;
		transactionsImported = 0;
		existingTransactionsSkipped = 0;
		importStatus = false;
		data.clear();
		double startTime = System.currentTimeMillis();
		
		while(scanner.hasNext()) {
			List<String> line = CSVUtils.parseLine(scanner.nextLine());
			
			lineNumber++;
			
			if (lineNumber<= 3) continue; // data starts on line 3
			
			/* account */
			
			// get rid of ' at front of accountName
			accountName = (line.get(5).charAt(0) == '"') ? line.get(5).substring(1) : line.get(5);
			accountNumber = (line.get(6).charAt(0) == '"') ? line.get(6).substring(1) : line.get(6);
			
			// add account if it doesn't exist in DB
			if (adao.findByName(accountName)==null) {
				account = new Account(accountName, accountNumber, NATWEST);
				importAccount(account);
			} else {
				account = adao.findByName(accountName);
			}
			
			
			/* statement */
			statement = sdao.findStatement(DateFormatter.formatLocally(line.get(0)),
					line.get(1),
					line.get(2),
					Double.parseDouble(line.get(3)),
					Double.parseDouble(line.get(4)),
					account);
			if (statement == null) { // if statement doesn't exist in db, create new one
				statement = new Statement(DateFormatter.formatLocally(line.get(0)),
						line.get(1),
						line.get(2),
						Double.parseDouble(line.get(3)),
						Double.parseDouble(line.get(4)),
						account);
				transactionsImported++;
			} else {
				existingTransactionsSkipped++;
			}
			
			LOGGER.info("Preparing statement for import: "+statement);
			data.add(statement);
			sdao.updateStatement(statement);
		}
		
		//update stats
		duration = (System.currentTimeMillis() - startTime)/1000; // convert to seconds
		transactionsImportedPerSecond = transactionsImported / (int)duration;
		scanner.close();
		importStatus = true;
	}
	
	public int getTransactionsImported() {
		return transactionsImported;
	}
	
	public int getExistingTransactionsSkipped() {
		return existingTransactionsSkipped;
	}

	public double getDuration() {
		return duration;
	}

	public int getTransactionsImportedPerSecond() {
		return transactionsImportedPerSecond;
	}
	
	public ObservableList<Statement> getData() {
		return data;
	}
	
	public void writeImportStatus(Label lblImportStatus, Label lblFileName, String filePath) {
		lblImportStatus.setVisible(true);
		lblFileName.setVisible(true);
		
		String fileName = filePath;
		int index = 0;
		for (int i = fileName.length()-1; i>=0; i--) {
			if (fileName.charAt(i) == '/' || fileName.charAt(i) == '\\') {
				index = i+1;
				break;
			}
		}
		
		fileName = fileName.substring(index);
		
		if (importStatus) {
			lblImportStatus.setText("Import Complete for file: ");
			lblFileName.setText(fileName);
		} else {
			lblImportStatus.setText("Import Failed for file: ");
			lblFileName.setText(fileName);
		}
		
	}


}


















