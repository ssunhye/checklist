import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class DBDriver extends Configured implements Tool{

	public int run(String[] args) throws Exception {
		
		Configuration conf = new Configuration();

	     DBConfiguration.configureDB(conf,
	     "com.mysql.jdbc.Driver",   // driver class
	     "jdbc:mysql://localhost:3306/checklist?serverTimezone=UTC", // db url
	     "root",    // user name
	     "ssafy406!@!@"); //password

	     Job job = Job.getInstance(conf);
		 job.setNumReduceTasks(4);

	     job.setJarByClass(DBDriver.class);
	     job.setMapperClass(DBMapper.class);
	     job.setReducerClass(DBReducer.class);
	     
	  	 // job.setReducerClass(DBReducer.class);
	     job.setMapOutputKeyClass(LongWritable.class);
	     job.setMapOutputValueClass(Text.class);
	    
	     job.setOutputKeyClass(DBOutputWritable.class);
	     job.setOutputValueClass(NullWritable.class);
	    
	     job.setInputFormatClass(TextInputFormat.class);
	     job.setOutputFormatClass(DBOutputFormat.class);

	     FileInputFormat.setInputPaths(job, new Path(args[0]));
	     
	     DBOutputFormat.setOutput(job, 
	    		 "review", //output table name
	    		 new String[] {"pcode", "category", "name", "title", "content", "score", "site", "date", "user_id", "image"} //table columns
	     );

	     System.exit(job.waitForCompletion(true) ? 0 : 1);
		
	     return 0;
	}
	
	public static void main(String[] args) {
		
		try{
		
		int result = ToolRunner.run(new Configuration(), new DBDriver(), args);
		
		System.out.println("job status ::"+result);
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
			
	}
}
