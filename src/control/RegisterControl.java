package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import jdbcs.Jdbc;
import model.Users;
import utils.ViewUtil;

public class RegisterControl implements Initializable {
	@FXML
	private Button registerBt;

	@FXML
	private Button resetBt;

	@FXML
	private TextField idText;

	@FXML
	private PasswordField pwText;

	@FXML
	private TextField nameText;
	@FXML
	private TextField sexText;
	@FXML
	private TextField ageText;
	@FXML
	private TextField telText;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void reset(ActionEvent event) {
		idText.setText("");
		pwText.setText("");
		nameText.setText("");
		sexText.setText("");
		ageText.setText("");
		telText.setText("");
	}

	public void register(ActionEvent event) {
		System.out.println("ע��");
		String id = idText.getText();
		String pw = pwText.getText();
		String name = nameText.getText();
		String sex = sexText.getText();
		String age = ageText.getText();
		String tel = telText.getText();
		if (!ViewUtil.checkId(id)) {
			ViewUtil.showErrorDialog("�˺Ÿ�ʽ���ԣ���ʽΪ6-13λ����");
		} else if (!ViewUtil.checkPassword(pw)) {
			ViewUtil.showErrorDialog("�����ʽ���ԣ���ʽΪ6-13λ���ֻ�����ĸ���");
		} else if (!ViewUtil.checkAge(age)) {
			ViewUtil.showErrorDialog("����ӦΪ����");
		} else if (ViewUtil.isEmpty(name)) {
			ViewUtil.showErrorDialog("��������Ϊ��");
		} else if (ViewUtil.isEmpty(sex)) {
			ViewUtil.showErrorDialog("�Ա���Ϊ��");
		} else if (ViewUtil.isEmpty(tel)) {
			ViewUtil.showErrorDialog("�绰����Ϊ��");
		} else {
			Users user = new Users(Long.parseLong(id), pw, name,
					Long.parseLong(age), sex, tel);
			if (new Jdbc().register(user)) {
				ViewUtil.showDialog("ע��ɹ�");
			} else {
				ViewUtil.showErrorDialog("ע��ʧ��");
			}
		}
	}
}
