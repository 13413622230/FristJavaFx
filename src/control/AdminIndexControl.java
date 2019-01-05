package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import jdbcs.Jdbc;
import model.Admin;
import model.Goods;
import model.Orders;
import utils.ViewUtil;
import view.LoginView;

public class AdminIndexControl implements Initializable {
	@FXML
	private Button findBt;

	@FXML
	private Button addGBt;
	@FXML
	private Button addBt;
	@FXML
	private Button delBt;
	@FXML
	private Button updateBt;
	@FXML
	private Button flushBt;
	@FXML
	private Label labelText;
	@FXML
	private TableView<Goods> goodTable;

	@FXML
	private TableView<Orders> OrderTable;
	@FXML
	private TableColumn<Goods, Long> idCol;
	@FXML
	private TableColumn<Goods, String> nameCol;
	@FXML
	private TableColumn<Goods, Double> priceCol;
	@FXML
	private TableColumn<Goods, Long> weightCol;
	@FXML
	private TableColumn<Goods, String> componentCol;
	@FXML
	private TableColumn<Goods, String> sourceCol;
	@FXML
	private TableColumn<Goods, String> applyCol;
	@FXML
	private TableColumn<Goods, String> typeCol;
	@FXML
	private TableColumn<Goods, String> praiseCol;
	@FXML
	private TableColumn<Goods, String> commonlyCol;
	@FXML
	private TableColumn<Goods, String> negativecommentCol;
	@FXML
	private TableColumn<Goods, Long> numCol;

	public ObservableList<Goods> List = FXCollections.observableArrayList();

	Admin admin;

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// System.out.println(user.getName());
		// labelText.setText(user.getName() + "����ӭ��");

		initGoodTable();

	}

	public void initGoodTable() {
		ObservableList<TableColumn<Goods, ?>> observableList = goodTable
				.getColumns();
		// idCol
		observableList.get(0).setCellValueFactory(
				new PropertyValueFactory("id"));
		// nameCol
		observableList.get(1).setCellValueFactory(
				new PropertyValueFactory("name"));
		// priceCol
		observableList.get(2).setCellValueFactory(
				new PropertyValueFactory("price"));
		// weightCol
		observableList.get(3).setCellValueFactory(
				new PropertyValueFactory("weight"));
		// componentCol
		observableList.get(4).setCellValueFactory(
				new PropertyValueFactory("component"));
		// observableList.get(4).setCellFactory(ProgressBarTableCell.forTableColumn());
		// sourceCol
		observableList.get(5).setCellValueFactory(
				new PropertyValueFactory("sources"));
		// applyCol
		observableList.get(6).setCellValueFactory(
				new PropertyValueFactory("apply"));
		// typeCol
		observableList.get(7).setCellValueFactory(
				new PropertyValueFactory("type"));
		// praiseCol
		observableList.get(8).setCellValueFactory(
				new PropertyValueFactory("praiseString"));
		// commonlyCol
		observableList.get(9).setCellValueFactory(
				new PropertyValueFactory("commonlyString"));
		// negativecommentCol
		observableList.get(10).setCellValueFactory(
				new PropertyValueFactory("negativecommentString"));
		// numCol
		observableList.get(11).setCellValueFactory(
				new PropertyValueFactory("num"));

		goodTable.setItems(List);
	}

	public void find() {
		String input = ViewUtil.showInputDialog("�������û���Ʒid������Ʒ����:");
		if (!(input == null || ViewUtil.isEmpty(input))) {
			int id = 0;
			try {
				id = Integer.parseInt(input);
			} catch (Exception e) {

			}
			List.clear();
			List.addAll(new Jdbc().findGoodById(id, input));
			// goodTable.setItems(List);
		}
	}

	public void evaluate() {

		System.out.println("����");
		Orders orders = OrderTable.getSelectionModel().getSelectedItem();
		long o_id = 0;
		long num = 0;
		long g_id = 0;
		if (orders != null) {
			int status = ViewUtil.showShoiceDialog();
			if (status >= 0) {
				o_id = orders.getId();
				num = orders.getNum();
				g_id = orders.getG_id();
				if (new Jdbc().isEvaluate((int) o_id)) {
					ViewUtil.showDialog("�����ۣ������ظ�����");
					return;
				} else {
					if (new Jdbc().addEvaluateNum(o_id, g_id, num, status)) {
						ViewUtil.showDialog("���۳ɹ���");
						// flushOrderTable();
					} else {
						ViewUtil.showDialog("����ʧ��");
					}
				}
			}

		} else
			ViewUtil.showDialog("��ѡ��Ҫ���۵Ķ���");
	}

	public void flushGoodsTable() {
		List.clear();
		List.addAll(new Jdbc().findAllGood());
	}

	public void logout() {
		Stage stage = (Stage) findBt.getScene().getWindow();
		stage.close();
		new LoginView().show();
	}

	// public void buy() {
	// // ViewUtil.showInputDialog("�������û���Ʒid������Ʒ����:");
	// System.out.println("����");
	// Goods good = goodTable.getSelectionModel().getSelectedItem();
	// int buyNum = 0;
	// if (good != null) {
	// String input = ViewUtil.showInputDialog("�����빺�������");
	// int g_id = good.getId().intValue();
	// try {
	// buyNum = Integer.parseInt(input);
	// if (buyNum > 0) {
	// if (new Jdbc().checkNum(g_id, buyNum)) {
	// double total = buyNum
	// * new Jdbc().findGoodById(g_id, "").get(0)
	// .getPrice();
	// Orders order = new Orders((long) 0, (long) g_id,
	// user.getId(), (long) buyNum, total, (long) 0);
	// if (new Jdbc().addNum(g_id, (0 - buyNum))
	// && new Jdbc().addOrder(order)) {
	// ViewUtil.showDialog("����ɹ�");
	// flushGoodsTable();
	// } else
	// ViewUtil.showErrorDialog("����ʧ��");
	// } else
	// ViewUtil.showErrorDialog("��治��");
	// } else {
	// ViewUtil.showErrorDialog("������������");
	// }
	// } catch (Exception e) {
	// ViewUtil.showErrorDialog("������������");
	// }
	// } else
	// ViewUtil.showDialog("��ѡ�������Ʒ");
	// }

	public void initLabel() {
		labelText.setText(admin.getName() + "����ӭ��");
	}

	public void showAddDialog() {
		Dialog<Map<String, String>> dialog = new Dialog<>();
		dialog.setTitle("�����Ʒ");
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
			try {
				Goods good = new Goods((long) 0, map.get("name"), Double
						.parseDouble(map.get("Price")), Long.parseLong(map
						.get("Weight")), map.get("Component"), map
						.get("Sources"), map.get("Apply"), map.get("Type"),
						(long) 0, (long) 0, (long) 0, Long.parseLong(map
								.get("Num")));
				if (new Jdbc().addGood(good)) {
					ViewUtil.showDialog("��ӳɹ�");
				} else {
					ViewUtil.showErrorDialog("���ʧ��");
				}
			} catch (Exception e) {
				ViewUtil.showErrorDialog("�ֶθ�ʽ����");
				return;
			}

			// System.out.println("Username=" + usernamePassword.getKey()
			// + ", Password=" + usernamePassword.getValue());
		});
	}

	// ������Ʒ��Ϣ��ť�¼�
	public void update() {
		Goods good = goodTable.getSelectionModel().getSelectedItem();
		if (good != null) {
			ArrayList<Goods> gs = new Jdbc().findGoodOne(good.getId()
					.intValue());
			if (gs.size() == 0) {
				ViewUtil.showErrorDialog("��Ʒ������");
			} else {
				good = gs.get(0);
				showUpdateDialog(good);
			}

		} else
			ViewUtil.showDialog("��ѡ��Ҫ�޸ĵ���Ʒ");
	}

	// ��Ʒ��Ʒ��ť�¼�
	public void delete() {
		Goods good = goodTable.getSelectionModel().getSelectedItem();
		if (good != null) {
			ArrayList<Goods> gs = new Jdbc().findGoodOne(good.getId()
					.intValue());
			if (gs.size() == 0) {
				ViewUtil.showErrorDialog("��Ʒ������");
			} else {
				if (new Jdbc().deleteGood(good.getId().intValue())) {
					ViewUtil.showDialog("ɾ���ɹ�");
				} else {
					ViewUtil.showErrorDialog("ɾ��ʧ��");
				}
			}

		} else
			ViewUtil.showDialog("��ѡ��Ҫɾ������Ʒ");
	}

	// ���ӿ�水ť�����¼�
	public void addNum() {
		Goods good = goodTable.getSelectionModel().getSelectedItem();
		if (good != null) {
			ArrayList<Goods> gs = new Jdbc().findGoodOne(good.getId()
					.intValue());
			if (gs.size() == 0) {
				ViewUtil.showErrorDialog("��Ʒ������");
			} else {
				try {
					String input = ViewUtil.showInputDialog("���������ӿ����:");
					int num = 0;
					num = Integer.parseInt(input);
					if (new Jdbc().addNum(good.getId().intValue(), num)) {
						ViewUtil.showDialog("���ӿ��ɹ�");
					} else {
						ViewUtil.showErrorDialog("���ӿ��ʧ��");
					}
				} catch (Exception e) {
					ViewUtil.showErrorDialog("�������ʽ����");
				}
			}

		} else
			ViewUtil.showDialog("��ѡ��Ҫ���ӿ�����Ʒ");
	}

	public void showUpdateDialog(Goods good) {
		Dialog<Map<String, String>> dialog = new Dialog<>();
		dialog.setTitle("�޸���Ʒ");
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

		TextField g_id = new TextField();
		g_id.setEditable(false);
		TextField g_name = new TextField();
		TextField Price = new TextField();
		TextField Weight = new TextField();
		TextField Component = new TextField();
		TextField Sources = new TextField();
		TextField Apply = new TextField();
		TextField Type = new TextField();
		TextField Num = new TextField();
		g_id.setText(good.getId() + "");
		g_name.setText(good.getName() + "");
		Price.setText(good.getPrice() + "");
		Weight.setText(good.getWeight() + "");
		Component.setText(good.getComponent() + "");
		Sources.setText(good.getSources() + "");
		Apply.setText(good.getApply() + "");
		Type.setText(good.getType() + "");
		Num.setText(good.getNum() + "");

		grid.add(new Label("��ƷID:"), 0, 0);
		grid.add(g_id, 1, 0);
		grid.add(new Label("��Ʒ����:"), 0, 1);
		grid.add(g_name, 1, 1);
		grid.add(new Label("��Ʒ�۸�:"), 0, 2);
		grid.add(Price, 1, 2);
		grid.add(new Label("��Ʒ����:"), 0, 3);
		grid.add(Weight, 1, 3);
		grid.add(new Label("��Ʒ�ɷ�:"), 0, 4);
		grid.add(Component, 1, 4);
		grid.add(new Label("��Ʒ��Դ:"), 0, 5);
		grid.add(Sources, 1, 5);
		grid.add(new Label("��ƷӦ��:"), 0, 6);
		grid.add(Apply, 1, 6);
		grid.add(new Label("��Ʒ����:"), 0, 7);
		grid.add(Type, 1, 7);
		grid.add(new Label("��Ʒ����:"), 0, 8);
		grid.add(Num, 1, 8);

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
				hm.put("id", g_id.getText());
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
			try {
				Goods goods = new Goods(Long.parseLong(map.get("id")), map
						.get("name"), Double.parseDouble(map.get("Price")),
						Long.parseLong(map.get("Weight")),
						map.get("Component"), map.get("Sources"), map
								.get("Apply"), map.get("Type"), (long) 0,
						(long) 0, (long) 0, Long.parseLong(map.get("Num")));
				if (new Jdbc().updateGood(goods)) {
					ViewUtil.showDialog("�޸ĳɹ�");
				} else {
					ViewUtil.showErrorDialog("�޸�ʧ��");
				}
			} catch (Exception e) {
				ViewUtil.showErrorDialog("�ֶθ�ʽ����");
				return;
			}

			// System.out.println("Username=" + usernamePassword.getKey()
			// + ", Password=" + usernamePassword.getValue());
		});
	}
}
