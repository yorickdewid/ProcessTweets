/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processtweets;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

/**
 *
 * @author eve
 */
public class ProcessTweets {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        JobClient client = new JobClient();
        JobConf conf = new JobConf(ProcessTweets.class);
        
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);
        
        FileInputFormat.addInputPath(conf, new Path("input"));
        FileOutputFormat.setOutputPath(conf, new Path("output2"));
        
        conf.setMapperClass(Mapper.class);
        conf.setReducerClass(Reducer.class);
        conf.setCombinerClass(Reducer.class);
        
        client.setConf(conf);
        try {
            JobClient.runJob(conf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
