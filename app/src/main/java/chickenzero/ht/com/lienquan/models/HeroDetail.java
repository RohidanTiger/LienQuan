package chickenzero.ht.com.lienquan.models;

import io.realm.RealmObject;

/**
 * Created by QuyDV on 4/12/17.
 */

public class HeroDetail extends RealmObject {
    private String id;
    private String attackDame;
    private String attackIncreaseDame;
    private String abilityDame;
    private String abilityIncreaseDame;
    private String hitPoint;
    private String hitPointIncrease;
    private String armor;
    private String armorIncrease;
    private String abilityArmor;
    private String abilityArmorIncrease;

    private String name;
    private String nickName;
    private String role;
    private String movementSpeed;
    private String range;
    private String lore;
    private String youtube;

    public HeroDetail() {
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public HeroDetail(String attackDame, String attackIncreaseDame, String abilityDame, String abilityIncreaseDame,
                      String hitPoint, String hitPointIncrease, String armor, String armorIncrease, String abilityArmor,
                      String abilityArmorIncrease, String name, String nickName, String role, String movementSpeed,
                      String range, String lore, String youtube) {
        this.attackDame = attackDame;
        this.attackIncreaseDame = attackIncreaseDame;
        this.abilityDame = abilityDame;
        this.abilityIncreaseDame = abilityIncreaseDame;
        this.hitPoint = hitPoint;
        this.hitPointIncrease = hitPointIncrease;
        this.armor = armor;
        this.armorIncrease = armorIncrease;
        this.abilityArmor = abilityArmor;
        this.abilityArmorIncrease = abilityArmorIncrease;
        this.name = name;
        this.nickName = nickName;
        this.role = role;
        this.movementSpeed = movementSpeed;
        this.range = range;
        this.lore = lore;
        this.youtube = youtube;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAttackDame() {
        return attackDame;
    }

    public void setAttackDame(String attackDame) {
        this.attackDame = attackDame;
    }

    public String getAttackIncreaseDame() {
        return attackIncreaseDame;
    }

    public void setAttackIncreaseDame(String attackIncreaseDame) {
        this.attackIncreaseDame = attackIncreaseDame;
    }

    public String getAbilityDame() {
        return abilityDame;
    }

    public void setAbilityDame(String abilityDame) {
        this.abilityDame = abilityDame;
    }

    public String getAbilityIncreaseDame() {
        return abilityIncreaseDame;
    }

    public void setAbilityIncreaseDame(String abilityIncreaseDame) {
        this.abilityIncreaseDame = abilityIncreaseDame;
    }

    public String getHitPoint() {
        return hitPoint;
    }

    public void setHitPoint(String hitPoint) {
        this.hitPoint = hitPoint;
    }

    public String getHitPointIncrease() {
        return hitPointIncrease;
    }

    public void setHitPointIncrease(String hitPointIncrease) {
        this.hitPointIncrease = hitPointIncrease;
    }

    public String getArmor() {
        return armor;
    }

    public void setArmor(String armor) {
        this.armor = armor;
    }

    public String getArmorIncrease() {
        return armorIncrease;
    }

    public void setArmorIncrease(String armorIncrease) {
        this.armorIncrease = armorIncrease;
    }

    public String getAbilityArmor() {
        return abilityArmor;
    }

    public void setAbilityArmor(String abilityArmor) {
        this.abilityArmor = abilityArmor;
    }

    public String getAbilityArmorIncrease() {
        return abilityArmorIncrease;
    }

    public void setAbilityArmorIncrease(String abilityArmorIncrease) {
        this.abilityArmorIncrease = abilityArmorIncrease;
    }

    public String getNickName() {
        return nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(String movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }
}
