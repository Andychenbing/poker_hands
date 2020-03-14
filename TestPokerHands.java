import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestPokerHands {

	@Test
	public void high_card_with_same_cards_are_equal() {                                 //���Ժڰ�˫����ɢ�ƴ�С���
		assertPokerHandsEquals("2D 3S 4H 5C 8S", "2S 3H 4C 5D 8D");
	}

	@Test
	public void high_card_with_only_one_card_different() {        //���Ժڰ�˫����ɢ�ƽ�һ�Ų���ͬ,
		assertPokerHandsLargerThan("2D 3H 4S 5C 10C", "2H 3S 4C 5D 8C");
		assertPokerHandsSmallerThan("2S 3H 4C 5D 6C", "2H 3C 4D 5S 8C");
	}

	
	@Test
	public void high_card_with_larger_highest_rank_but_smaller_second_highest_card_rank() {    //���Ժڰ�˫����ɢ���ж����ͬ
		assertPokerHandsLargerThan("2S 4H 6S 7C AD", "2S 4H 6S 8C KD");
	}
	
	@Test
	public void pair_is_higher_than_high_card() {                      //���Զ��ӱ�ɢ�ƴ�
		assertPokerHandsLargerThan("2S 2H 3S 4C 5D", "9S JH QS KC AD");
		assertPokerHandsSmallerThan("9S JS QS KS AD", "2S 2H 3S 4C 5D");
		assertPokerHandsLargerThan("2S 3H 3S 4C 5D", "9S JH QS KC AD");
	}
	
	@Test
	public void pair_compare_to_pair_by_pair_card_rank() {       //���Ժڰ�˫�����Ӵ�С����ʱ���
		assertPokerHandsSmallerThan("2S 3H 6C 6D 8S", "2S 3H 5S 7C 7D");
		assertPokerHandsLargerThan("2S 3H 5S 8C 8D", "2S 3H 5S 7C 7D");
		assertPokerHandsLargerThan("2S 3H 8S 8C 9D", "2S 7H 7S 8C 9D");
	}
	
	@Test
	public void pair_compare_to_pair_by_rest_of_high_card() {    //���Ժڰ�˫���������ʱ���Ƚ�ɢ�ƴ�С
		assertPokerHandsLargerThan("2S 2H 3S 4C 6D", "2S 2H 3S 4C 5D");
		assertPokerHandsSmallerThan("2S 2H 3S 4C 5D", "2S 2H 3S 4C 6D");
		assertPokerHandsLargerThan("2S 2H 3S 5C 7D", "2S 2H 3S 4C 7D");
		assertPokerHandsLargerThan("9S 9H 7S 8C QD", "2S 3H 9S 9C QD");
	}
	
	@Test
	public void two_pairs_is_higher_than_pair() {      //�����������Ӵ���1������
		assertPokerHandsLargerThan("2S 2H 3S 3C 4D", "JS QH KS AC AD");
		assertPokerHandsSmallerThan("JS QH KS AC AD", "2S 2H 3S 3C 4D");
		assertPokerHandsLargerThan("2S 3H 3S 4C 4D", "JS QH KS AC AD");
		assertPokerHandsLargerThan("2S 2H 3S 4C 4D", "JS QH KS AC AD");
	}
	
	@Test
	public void two_pairs_compare_to_two_pairs_by_pair_card_rank() {   //���Ժڰ�˫�������������ӣ����������Ӳ���ȫ���
		assertPokerHandsLargerThan("2S 2H 3S 5C 5D", "2S 2H 3S 4C 4D");
		assertPokerHandsLargerThan("2S 3H 3S 5C 5D", "2S 2H 3S 5C 5D");
	}
	
	@Test
	public void two_pairs_compare_to_two_pairs_by_remaining_card_rank() {    //���Ժڰ�˫�������������ӣ�������������ȫ��ȣ��Ƚ�ɢ�ƴ�С
		assertPokerHandsLargerThan("3H 3S 5C 5D 7S", "3H 3S 5C 5D 6S");
	}
	
	@Test
	public void three_of_a_kind_is_higher_than_two_pairs() {                 //����������������
		assertPokerHandsLargerThan("2H 2S 2C 3D 4S", "QH KS KC AD AS");
		assertPokerHandsSmallerThan("QH KS KC AD AS", "2H 2S 2C 3D 4S");
		assertPokerHandsLargerThan("2H 3S 3C 3D 4S", "QH KS KC AD AS");
		assertPokerHandsLargerThan("2H 3S 4C 4D 4S", "QH KS KC AD AS");
	}
	
	@Test
	public void three_of_a_kind_compare_to_three_of_a_kind() {                 //���Ժڰ�˫����������ʱ�Ĵ�С
		assertPokerHandsLargerThan("2H 3S 3C 3D 4S", "2H 2S 2C 3D 4S");
	}
	
	@Test
	public void straight_is_higher_than_three_of_a_kind() {                    //����˳�Ӵ�������
		assertPokerHandsLargerThan("2H 3S 4C 5D 6S", "QH KS AC AD AS");
		assertPokerHandsSmallerThan("QH KS AL AD AS", "2H 3S 4C 5D 6S");
	}
	
	@Test
	public void straight_compare_to_straight_by_highest_card_rank() {       //���Ժڰ�˫������˳��ʱ��˭��˳�Ӵ�
		assertPokerHandsLargerThan("3S 4C 5D 6S 7H", "2H 3S 4C 5D 6S");
	}
	
	@Test
	public void flush_is_higher_than_straight() {                           //����ͬ������˳��
		assertPokerHandsLargerThan("2S 2S 2S 3S 4S", "TH JS QC KD AS");
		assertPokerHandsSmallerThan("TH JS QC KD AS", "2S 2S 2S 3S 4S");
	}
	
	@Test
	public void flush_compare_to_flush_by_high_card_ranks() {         //���Ժڰ�˫��ͬ����С
		assertPokerHandsLargerThan("2S 2S 2S 3S TS", "2H 3H 4H 5H 9H");
	}
	
	@Test
	public void fullhouse_is_higher_than_flush() {                     //���Ժ�«��ͬ����
		assertPokerHandsLargerThan("2S 2C 2D 3S 3H", "2S 2S 2S 3S 4S");
		assertPokerHandsSmallerThan("2S 2S 2S 3S 4S", "2S 2C 2D 3S 3H");
		assertPokerHandsLargerThan("2S 2C 3D 3S 3H", "2S 2S 2S 3S 4S");
	}
	
	@Test
	public void fullhouse_compare_to_fullhouse_by_three_of_a_kind() {     //���Ժڰ�˫�����Ǻ�«���Ƚϴ�С
		assertPokerHandsLargerThan("2S 2C 3D 3S 3H", "2S 2C 2D 3S 3H");
	}
	
	@Test
	public void four_of_a_kind_is_higher_than_fullhouse() {                //������֧�Ⱥ�«��
		assertPokerHandsLargerThan("2S 2C 2D 2S 3H", "KS KC AD AS AH");
		assertPokerHandsSmallerThan("KS KC AD AS AH", "2S 2C 2D 2S 3H");
		assertPokerHandsLargerThan("2S 3C 3D 3S 3H", "KS KC AD AS AH");
	}
	
	@Test
	public void four_of_a_kind_compare_to_four_of_a_kind_by_card_rank() {             //���Ժڰ�˫��������֧���Ƚϴ�С
		assertPokerHandsLargerThan("2S 3C 3D 3S 3H", "2S 2C 2D 2S 3H");
	}
	
	@Test
	public void straight_flush_is_higher_than_four_of_a_kind() {                                //����ͬ��˳����֧��
		assertPokerHandsLargerThan("2S 3S 4S 5S 6S", "KS AC AD AS AH");
		assertPokerHandsSmallerThan("KS AC AD AS AH", "2S 3S 4S 5S 6S");
	}
	
	@Test
	public void straight_flush_compare_to_straight_flush_by_highest_card_rank() {     //���Ժڰ�˫������ͬ��˳���Ƚϴ�С
		assertPokerHandsLargerThan("3S 4S 5S 6S 7S", "2S 3S 4S 5S 6S");
	}