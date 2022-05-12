public class Entity {
    String name;
    Weapon equipped_weapon;
    Spell equipped_spell;
    int max_mana;
    int max_health;
    int current_health;
    int current_mana;
    int action_speed; // 1 is normal, 2 means two actions before enemy 

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

    public void setName(String name){
        this.name = name;
    }

    public void setMaxHealth(int max_hp){
        this.max_health = max_hp;
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

    public void heal(int heal_val){
        if (heal_val+this.getHealth()<this.getMaxHealth()){
            this.setHealth(heal_val+this.getHealth());
        }else{
            this.setHealth(this.getMaxHealth());
        }
    }

    
}
