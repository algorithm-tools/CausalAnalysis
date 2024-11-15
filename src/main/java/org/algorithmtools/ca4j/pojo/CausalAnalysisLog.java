package org.algorithmtools.ca4j.pojo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;

public class CausalAnalysisLog {

    private static final Logger LOGGER = LoggerFactory.getLogger(CausalAnalysisLog.class);

    private final LinkedList<Log> logList;

    public CausalAnalysisLog() {
        this.logList = new LinkedList<>();
    }

    public void add(String level, String topic, String text){
        this.logList.add(new Log(level,"[" + topic + "]", text));
    }

    public void info(String msg){
        LOGGER.info(msg);
    }

    public void error(String msg){
        LOGGER.error(msg);
    }

    public void warn(String msg){
        LOGGER.warn(msg);
    }

    public void printThenClear(){
        for (Log log : logList) {
            if("debug".equals(log.level)){
                LOGGER.debug("{}: {}", log.topic, log.text);
            } else if("warn".equals(log.level)){
                LOGGER.warn("{}: {}", log.topic, log.text);
            } else if("error".equals(log.level)){
                LOGGER.error("{}: {}", log.topic, log.text);
            } else {
                LOGGER.info("{}: {}", log.topic, log.text);
            }
        }
        this.logList.clear();
    }

    public static class Log{
        private String level;
        private String topic;
        private String text;

        public Log(String level, String topic, String text) {
            this.level = level;
            this.topic = topic;
            this.text = text;
        }
    }
}
