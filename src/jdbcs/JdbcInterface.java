package jdbcs;

import java.util.ArrayList;

import model.Goods;
import model.Orders;
import model.Users;

public interface JdbcInterface {
	/**
	 * @param data
	 *            ������
	 * @param type
	 *            0���û���½��1�ǹ���Ա��½
	 * @return users����adminʵ����
	 */
	public Object login(Object data, int type);

	/**
	 * @param user
	 *            ע�������
	 * @return ע����
	 */
	public boolean register(Users user);

	/**
	 * @param g_id
	 *            ����Ʒid
	 * @param g_name
	 *            ����Ʒ���� ģ������
	 * @return ��Ʒlist
	 */
	public ArrayList<Goods> findGoodById(int g_id, String g_name);

	/**
	 * @return ȫ����Ʒ��list
	 */
	public ArrayList<Goods> findAllGood();

	/**
	 * @param g_id
	 *            ��Ʒid
	 * @param num
	 *            ���ӿ������,�����ʱ�������Ǹ���
	 * @return ���ӽ��
	 */
	public boolean addNum(int g_id, int num);

	/**
	 * @param good
	 *            ��Ʒ������
	 * @return ������Ʒ���
	 */
	public boolean addGood(Goods good);

	/**
	 * @param g_id
	 *            ��Ʒid
	 * @return ɾ�����
	 */
	public boolean deleteGood(int g_id);

	/**
	 * @param good
	 *            ��Ʒ������
	 * @return ���
	 */
	public boolean updateGood(Goods good);

	/**
	 * @param data
	 *            ������
	 * @param type
	 *            0���û��޸����룬1�ǹ���Ա�޸�����
	 * @return ���
	 */
	public boolean updatePassword(Object data, int type);

	/**
	 * @param g_id
	 *            ��Ʒid
	 * @param num
	 *            ��������
	 * @param type
	 *            0�ǲ�����1��������2�Ǻ���
	 * @return ���
	 */
	public boolean addEvaluateNum(long o_id, long g_id, long num, int type);

	/**
	 * @param o_id
	 *            ����id
	 * @return ����list
	 */
	public ArrayList<Orders> findOrdersByOId(int o_id);

	/**
	 * @param u_id
	 *            �û�id
	 * @return
	 */
	public ArrayList<Orders> findOrdersByUId(int u_id);

	/**
	 * @return ȫ������list
	 */
	public ArrayList<Orders> findAllOrders();

	/**
	 * @param order
	 *            ������
	 * @return ���
	 */
	public boolean addOrder(Orders order);

	/**
	 * @param o_id
	 *            ����id
	 * @return
	 */
	public boolean deleteOrderByOid(int o_id);

	/**
	 * @param g_id
	 *            ��Ʒid
	 * @return
	 */
	public boolean deleteOrderByGid(int g_id);

	/**
	 * @param o_id
	 *            ����id
	 * @return
	 */
	public boolean updateOrderStatus(int o_id);

	/**
	 * �����
	 * 
	 * @param g_id
	 * @param num
	 * @return
	 */
	public boolean checkNum(int g_id, int num);
}
