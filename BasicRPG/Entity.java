package BasicRPG;

public abstract class Entity {
    protected String name;
    protected Weapon equippedWeapon;
    protected Spell equippedSpell;
    protected int maxMana, maxHealth, currentHealth, currentMana, defense;
    protected int actionSpeed; // 1 is normal, 2 means two actions per turn
    protected boolean alive;

    public Entity(String name, int maxMana, int maxHealth, int actionSpeed, int defense){
        this.name = name;
        this.maxMana = maxMana;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.currentMana = maxMana;
        this.actionSpeed = actionSpeed;
        this.defense = defense;
        this.alive = true;
    }

    public String getName(){
        return this.name;
    }

    public Weapon getEquippedWeapon(){
        return this.equippedWeapon;
    }

    public Spell getEquippedSpell(){
        return this.equippedSpell;
    }

    public int getActionSpeed(){
        return this.actionSpeed;
    }

    public int getMaxMana(){
        return this.maxMana;
    }

    public int getMaxHealth(){
        return this.maxHealth;
    }

    public int getHealth(){
        return this.currentHealth;
    }

    public int getMana(){
        return this.currentMana;
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
        this.maxHealth = max_hp;
    }

    public void setMaxMana(int maxMana){
        this.maxMana = maxMana;
    }

    public void setWeapon(Weapon wep){
        this.equippedWeapon = wep;
    }

    public void setSpell(Spell spell){
        this.equippedSpell = spell;
    }

    public void setHealth(int health){
        this.currentHealth = health;
    }

    public void setMana(int mana){
        this.currentMana = mana;
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
        System.out.printf("\n%s has been slain!\n",this.getName());
    }

}
