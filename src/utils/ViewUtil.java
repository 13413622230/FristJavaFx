/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author wuzhuhao
 */
public class ViewUtil {

	// �������
	public static boolean checkPassword(String pw) {
		boolean flag = false;
		try {
			String check = "^[\\d|A-Za-z]{6,13}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(pw);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	// ����˺�
	public static boolean checkId(String id) {
		boolean flag = false;
		try {
			String check = "^[\\d]{6,13}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(id);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	// ���age
	public static boolean checkAge(String age) {
		boolean flag = false;
		try {
			String check = "^[\\d]*$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(age);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	// ����Ƿ�Ϊ��
	public static boolean isEmpty(String str) {
		boolean flag = false;
		try {
			String check = "^\\s*$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(str);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public static void main(String[] args) {

	}

	public static void initJTable(JTable jtable, String[][] rowdata) {
		DefaultTableModel tableModel = (DefaultTableModel) jtable.getModel();
		tableModel.setRowCount(0);
		for (int i = 0; i < rowdata.length; i++) {
			tableModel.addRow(rowdata[i]);
		}
		jtable.invalidate();
	}

	// public static String[][] getStaff(ArrayList<Staff> data) {
	// String[][] rowdata = new String[data.size()][4];
	// for (int i = 0; i < rowdata.length; i++) {
	// rowdata[i] = new String[] { data.get(i).getNumber() + "",
	// data.get(i).getName(), data.get(i).getSex(),
	// data.get(i).getAge() + "" };
	// }
	// return rowdata;
	// }
	//
	// public static String[][] getActivity(ArrayList<Activity> data) {
	// String[][] rowdata = new String[data.size()][];
	// for (int i = 0; i < rowdata.length; i++) {
	// rowdata[i] = new String[] { data.get(i).getNumber() + "",
	// data.get(i).getName(), data.get(i).getSponsor(),
	// data.get(i).getDescribe(), data.get(i).getNature(),
	// data.get(i).getCost() + "", data.get(i).getTime() + "����" };
	// }
	// return rowdata;
	// }
	//
	// public static String[][] getActivityForVip(ArrayList<Activity> data) {
	// String[][] rowdata = new String[data.size()][];
	// for (int i = 0; i < rowdata.length; i++) {
	// rowdata[i] = new String[] { data.get(i).getNumber() + "",
	// data.get(i).getName(), data.get(i).getSponsor(),
	// data.get(i).getDescribe(), data.get(i).getNature(),
	// data.get(i).getCost() + "", data.get(i).getTime() + "����",
	// data.get(i).getStatus() == 0 ? "δ�μ�" : "�Ѳμ�" };
	// }
	// return rowdata;
	// }
	//
	// public static String[][] getVip(ArrayList<Vip> data) {
	// String[][] rowdata = new String[data.size()][];
	// for (int i = 0; i < rowdata.length; i++) {
	// rowdata[i] = new String[] { data.get(i).getNumber() + "",
	// data.get(i).getPassword(), data.get(i).getUsername(),
	// data.get(i).getAge() + "", data.get(i).getSum() + "",
	// data.get(i).getEndtime() + "��",
	// data.get(i).getStatus() == 0 ? "��ʧ" : "����",
	// data.get(i).getTel() };
	// }
	// return rowdata;
	// }

	public static void selectOne(JTable jtable) {
		int row[] = jtable.getSelectedRows();
		if (row.length == 0) {
			throw new RuntimeException("û��ѡ�У�");
		} else if (row.length == 1) {

		} else
			throw new RuntimeException("����ͬʱѡ���������ϣ�");
	}

	public static void showDialog(String tip) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("��ʾ��");
		alert.setHeaderText(null);
		alert.setContentText(tip);
		alert.showAndWait();
	}

	public static void showErrorDialog(String tip) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("������ʾ");
		alert.setHeaderText(null);
		alert.setContentText(tip);
		alert.showAndWait();
	}

	public static String showInputDialog(String tip) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("������ʾ��");
		dialog.setHeaderText(null);
		dialog.setContentText(tip);

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			System.out.println("Your input: " + result.get());
			return result.get();
		} else {
			return null;
		}

		// // The Java 8 way to get the response value (with lambda expression).
		// result.ifPresent(name -> System.out.println("Your name: " + name));
	}

	public static int showShoiceDialog() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("����");
		alert.setHeaderText(null);
		alert.setContentText("��ѡ�����۳̶ȣ�");

		ButtonType buttonTypeOne = new ButtonType("����");
		ButtonType buttonTypeTwo = new ButtonType("����");
		ButtonType buttonTypeThree = new ButtonType("����");
		ButtonType buttonTypeCancel = new ButtonType("ȡ��",
				ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo,
				buttonTypeThree, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne) {
			return 2;
		} else if (result.get() == buttonTypeTwo) {
			return 1;
		} else if (result.get() == buttonTypeThree) {
			return 0;
		} else {
			return -1;
		}
	}

	public static void showAddDialog(String tip) {
		Dialog<Map<String, String>> dialog = new Dialog<>();
		dialog.setTitle(tip);
		dialog.setHeaderText(null);

		// Set the icon (must be included in the project).
		// dialog.setGraphic(new ImageView("/img/6.jpg"));

		// Set the button types.
		ButtonType loginButtonType = new ButtonType("����", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes()
				.addAll(loginButtonType, ButtonType.CANCEL);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField g_name = new TextField();
		g_name.setPromptText("��Ʒ����");
		TextField Price = new TextField();
		Price.setPromptText("��Ʒ�۸�");
		TextField Weight = new TextField();
		Weight.setPromptText("��Ʒ����");
		TextField Component = new TextField();
		Component.setPromptText("��Ʒ�ɷ�");
		TextField Sources = new TextField();
		Sources.setPromptText("��Ʒ��Դ");
		TextField Apply = new TextField();
		Apply.setPromptText("��ƷӦ��");
		TextField Type = new TextField();
		Type.setPromptText("��Ʒ����");
		TextField Num = new TextField();
		Num.setPromptText("��Ʒ����");

		grid.add(new Label("��Ʒ����:"), 0, 0);
		grid.add(g_name, 1, 0);
		grid.add(new Label("��Ʒ�۸�:"), 0, 1);
		grid.add(Price, 1, 1);
		grid.add(new Label("��Ʒ����:"), 0, 2);
		grid.add(Weight, 1, 2);
		grid.add(new Label("��Ʒ�ɷ�:"), 0, 3);
		grid.add(Component, 1, 3);
		grid.add(new Label("��Ʒ��Դ:"), 0, 4);
		grid.add(Sources, 1, 4);
		grid.add(new Label("��ƷӦ��:"), 0, 5);
		grid.add(Apply, 1, 5);
		grid.add(new Label("��Ʒ����:"), 0, 6);
		grid.add(Type, 1, 6);
		grid.add(new Label("��Ʒ����:"), 0, 7);
		grid.add(Num, 1, 7);

		// Enable/Disable login button depending on whether a username was
		// entered.
		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(false);

		// g_name.textProperty().addListener((observable, oldValue, newValue) ->
		// {
		// loginButton.setDisable(newValue.trim().isEmpty());
		// });

		dialog.getDialogPane().setContent(grid);

		// Request focus on the username field by default.
		// Platform.runLater(() -> g_name.requestFocus());

		// Convert the result to a username-password-pair when the login button
		// is clicked.
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == loginButtonType) {
				HashMap<String, String> hm = new HashMap<String, String>();
				hm.put("name", g_name.getText());
				hm.put("Price", Price.getText());
				hm.put("Weight", Weight.getText());
				hm.put("Component", Component.getText());
				hm.put("Sources", Sources.getText());
				hm.put("Apply", Apply.getText());
				hm.put("Type", Type.getText());
				hm.put("Num", Num.getText());
				return hm;
			}
			return null;
		});

		Optional<Map<String, String>> result = dialog.showAndWait();

		result.ifPresent(map -> {

			// System.out.println("Username=" + usernamePassword.getKey()
			// + ", Password=" + usernamePassword.getValue());
		});
	}
}
