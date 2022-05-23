package BasicRPG;

public abstract class Entity {
    protected String name;
    protected Weapon equipped_weapon;
    protected Spell equipped_spell;
    protected int max_mana;
    protected int max_health;
    protected int current_health;
    protected int current_mana;
    protected int action_speed; // 1 is normal, 2 means two actions per turn
    protected boolean alive;
    protected int defense;

    public Entity(String name, int max_mana, int max_health, int action_speed, int defense){
        this.name = name;
        this.max_mana = max_mana;
        this.max_health = max_health;
        this.current_health = max_health;
        this.current_mana = max_mana;
        this.action_speed = action_speed;
        this.defense = defense;
        this.alive = true;
    }

    public String getName(){
        return this.name;
    }

    public Weapon getEquippedWeapon(){
        return this.equipped_weapon;
    }

    public Spell getEquippedSpell(){
        return this.equipped_spell;
    }

    public int getActionSpeed(){
        return this.action_speed;
    }

    public int getMaxMana(){
        return this.max_mana;
    }

    public int getMaxHealth(){
        return this.max_health;
    }

    public int getHealth(){
        return this.current_health;
    }

    public int getMana(){
        return this.current_mana;
    }

    public int getDefense(){
        return this.defense;
    }

    public boolean checkVitals(){
        return this.alive;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setMaxHealth(int max_hp){
        this.max_health = max_hp;
    }

    public void setMaxMana(int max_mana){
        this.max_mana = max_mana;
    }

    public void setWeapon(Weapon wep){
        this.equipped_weapon = wep;
    }

    public void setSpell(Spell spell){
        this.equipped_spell = spell;
    }

    public void setHealth(int health){
        this.current_health = health;
    }

    public void setMana(int mana){
        this.current_mana = mana;
    }

    public void setDefense(int defense){
        this.defense = defense;
    }

    public void heal(int heal_val){
        if (heal_val+this.getHealth()<this.getMaxHealth()){     // check we dont exceed max
            this.setHealth(heal_val+this.getHealth());
            System.out.println("\n"+this.getName() + " healed for " + heal_val + " health\n");
        }else{
            System.out.println("\n"+this.getName() + " healed for " + (this.getMaxHealth()-this.getHealth()) + " health\n");
            this.setHealth(this.getMaxHealth());                // if we hit max, heal to max
        }
    }

    public void restoreMana(int restore_val){                   //same function as heal
        if (restore_val+this.getMana()<this.getMaxMana()){
            this.setMana(restore_val+this.getMana());
            System.out.println("\n"+this.getName() + " restored " + restore_val + " mana\n");
        }else{
            System.out.println("\n"+this.getName() + " restored " + (this.getMaxMana()-this.getMana()) + " mana\n");
            this.setMana(this.getMaxMana());
        }
    }

    public void takeDMG(int damage){                            //setting health to a reducton corresponding to damage
        this.setHealth(this.getHealth()-damage);
        if (this.getHealth()<=0){
            killEntity();
        }
    }

    public void killEntity(){
        this.alive = false;
        System.out.printf("\n%s has been slain\n",this.getName());
    }

}
