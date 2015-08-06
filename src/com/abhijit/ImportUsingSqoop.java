package com.abhijit;

public class ImportUsingSqoop {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SqoopOpt options = new SqoopOptions();
		options.setConnectString("jdbc:oracle:thin:@devrac1-vip.valuecentric.com:1521/reportdv_serv");
		options.setTableName("TABLE_NAME");
		options.setWhereClause("id>10");    
		options.setUsername("USERNAME");
		options.setPassword("PASSWORD");
		options.setDirectMode(true);    // Turning the direct mode off when importing data to HBase
		options.setNumMappers(8);         // Default value is 4
		options.setSqlQuery("SELECT * FROM user_logs WHERE $CONDITIONS limit 10");
		options.setSplitByCol("log_id");

		// HBase options
		options.setHBaseTable("HBASE_TABLE_NAME");
		options.setHBaseColFamily("colFamily");
		options.setCreateHBaseTable(true);    // Create HBase table, if it does not exist
		options.setHBaseRowKeyColumn("log_id");

		int ret = new ImportTool().run(options);
		if (ret != 0) {
			throw new RuntimeException("Sqoop failed - return code " + Integer.toString(ret));
		}
	}
}
//After this, 
//1.I need to download the file
//2. create a table in hive manually.
//3. Load this downloaded file into the hive table.
//To directly move data using sqoop to the hive, there is --hive-import in sqoop command line.
//options.setHiveImport(true);
//options.setOverwriteHiveTable(true);
//Useful link: https://sqoop.apache.org/docs/1.4.3/SqoopUserGuide.html#_selecting_the_data_to_import



/*import net.neoremind.sshxcute.core.SSHExec;
import net.neoremind.sshxcute.core.ConnBean;
import net.neoremind.sshxcute.task.CustomTask;
import net.neoremind.sshxcute.task.impl.ExecCommand;

public class ImportbySSH {

public static void main(String args[]) throws Exception{

    // Initialize a ConnBean object, parameter list is ip, username, password

    ConnBean cb = new ConnBean("devhdp01.valuecentric.com", "root","hadoop");

    SSHExec ssh = SSHExec.getInstance(cb);          
    // Connect to server
    ssh.connect();
    CustomTask sampleTask1 = new ExecCommand("echo $SSH_CLIENT"); // Print Your Client IP By which you connected to ssh server on Horton Sandbox
    System.out.println(ssh.exec(sampleTask1));
    CustomTask sampleTask2 = new ExecCommand("sqoop import --connect jdbc:mysql://devhdp01:3316/mysql_db_name --username=user --password=pwd --table table_name --hive-import -m 1 -- --schema default");
    ssh.exec(sampleTask2);
    ssh.disconnect();   
}
}*/
