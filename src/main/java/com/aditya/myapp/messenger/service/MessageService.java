package com.aditya.myapp.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.aditya.myapp.messenger.database.DatabaseClass;
import com.aditya.myapp.messenger.exception.DataNotFoundException;
import com.aditya.myapp.messenger.model.Message;

public class MessageService {

	private Map <Long, Message> messages = DatabaseClass.getMessages();
	
	public List<Message> getMessages(){
		return new ArrayList<Message>(messages.values());
	}
	
	public Message getMessage(long id){
		if(messages.get(id) == null){
			throw new DataNotFoundException(String.format("No meesage for message id %d found in the system.", id));
		}
		return messages.get(id);
	}
	
	public Message addMessage(Message message){
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message){
		if(message.getId() <= 0){
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id){
		return messages.remove(id);
	}
	
	public List<Message> getMessagesForYear(int year){
		List <Message> finalList = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		for(Message message : messages.values()){
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR) == year){
				finalList.add(message);
			}
		}
		return finalList;
	}
	
	public List<Message> getMessagesPeginated(int start, int size){
		List <Message> msgValues =  new ArrayList<Message>(messages.values());
		if(start + size > msgValues.size()) return new ArrayList<Message>();
		return msgValues.subList(start, start+size);
	}
}
