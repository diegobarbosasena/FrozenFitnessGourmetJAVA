package br.com.ajudantes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Mascaras {


	private static List<KeyCode> ignoreKeyCodes;
	private static KeyCode F1;
	private static KeyCode F2;
	private static KeyCode F3;
	private static KeyCode F4;
	private static KeyCode F5;
	private static KeyCode F6;
	private static KeyCode F7;
	private static KeyCode F8;
	private static KeyCode F9;
	private static KeyCode F10;
	private static KeyCode F11;
	private static KeyCode F12;

	static {	    
		ignoreKeyCodes = new ArrayList<>();
	    Collections.addAll(ignoreKeyCodes, new KeyCode[]{F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12});
	}
	
	public static void ignoreKeys(final TextField textField) {
	    textField.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent keyEvent) {
	            if (ignoreKeyCodes.contains(keyEvent.getCode())) {
	                keyEvent.consume();
	            }
	        }
	    });
	}
	
	public static void numericField(final TextField textField) {
	    textField.lengthProperty().addListener(new ChangeListener<Number>() {
	        @Override
	        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
	            if (newValue.intValue() > oldValue.intValue()) {
	                char ch = textField.getText().charAt(oldValue.intValue());
	                if (!(ch >= '0' && ch <= '9')) {
	                    textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
	                }
	            }
	        }
	    });
	}
	
	public static void cepMask (final TextField textFieldCep){
		
		maxField(textFieldCep, 9); 
		
		textFieldCep.lengthProperty().addListener(new ChangeListener<Number>() {
	        @Override
	        public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
	        	String value = textFieldCep.getText();
	            value = value.replaceAll("[^0-9]", "");
	            value = value.replaceFirst("(\\d{5})(\\d)", "$1-$2");
	            textFieldCep.setText(value);
	            positionCaret(textFieldCep);
	        }
	    });
	}
	
	public static void cnpjField(final TextField textField) {
	    maxField(textField, 18);
	
	    textField.lengthProperty().addListener(new ChangeListener<Number>() {
	        @Override
	        public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
	            String value = textField.getText();
	            value = value.replaceAll("[^0-9]", "");
	            value = value.replaceFirst("(\\d{2})(\\d)", "$1.$2");
	            value = value.replaceFirst("(\\d{2})\\.(\\d{3})(\\d)", "$1.$2.$3");
	            value = value.replaceFirst("\\.(\\d{3})(\\d)", ".$1/$2");
	            value = value.replaceFirst("(\\d{4})(\\d)", "$1-$2");
	            textField.setText(value);
	            positionCaret(textField);
	        }
	    });
	}
	
	public static void telefoneMask (final TextField textFieldTel){
		
		maxField(textFieldTel, 13);
		
		textFieldTel.lengthProperty().addListener(new ChangeListener<Number>() {
	        @Override
	        public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
	            String value = textFieldTel.getText();
	            value = value.replaceAll("[^0-9]", "");
	            value = value.replaceFirst("(\\d{0})(\\d)", "$1($2");
	            value = value.replaceFirst("(\\d{2})(\\d)", "$1)$2");
	            value = value.replaceFirst("(\\d{4})(\\d)", "$1-$2");
	            textFieldTel.setText(value);
	            positionCaret(textFieldTel);
	        }
	    }); 
	}
	
	private static void positionCaret(final TextField textField) {
	    Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	            // Posiciona o cursor sempre a direita.
	            textField.positionCaret(textField.getText().length());
	        }
	    });
	}
	
	private static void maxField(final TextField textField, final Integer length) {
	    textField.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
	            if (newValue.length() > length)
	                textField.setText(oldValue);
	        }
	    });
	}
}