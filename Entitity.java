
public abstract class Entitity {
	
	static int MAX_HP;
	static int HP;
	
	static int ATK;
	static int DEF;
	
	static int EXP;
	
	static int Gold;
	
	static int GetHP() {
		return HP;
	}
	
	static int GetATK() {
		return ATK;
	}
	static int GetDef() {
		return DEF;
	}
	static int GetExp() {
		return EXP;
	}
	static int GetGold() {
		return Gold;
	}

	private void SetATK(int atk) {
		ATK=atk;
	}
	private void SetDef(int def) {
		DEF=def;
	}
	private void SetExp(int exp) {
		EXP=exp;
	}
	private void SetGold(int gold) {
		Gold=gold;
	}
	
}
