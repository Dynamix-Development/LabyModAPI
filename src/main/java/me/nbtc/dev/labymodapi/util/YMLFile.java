package me.nbtc.dev.labymodapi.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public abstract class YMLFile
{
    public String path;
    private final File file;
    private YamlConfiguration cfg;
    
    public YMLFile(final String name, final String path) {
        this.path = path;
        new File(path).mkdir();
        this.file = new File(path, name);
    }
    
    public void createNew() {
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
                this.cfg = YamlConfiguration.loadConfiguration(this.file);
                this.setDefaults();
            }
            catch (Exception ex) {}
        }
        if (this.cfg == null) {
            this.cfg = YamlConfiguration.loadConfiguration(this.file);
        }
    }
    
    public boolean exists() {
        return this.file.exists();
    }
    
    public void delete() {
        this.file.delete();
        this.cfg = null;
    }
    
    public YMLFile(final String name) {
        new File(this.path).mkdir();
        this.file = new File(this.path, name);
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
                this.cfg = YamlConfiguration.loadConfiguration(this.file);
                this.setDefaults();
            }
            catch (Exception ex) {}
        }
        this.cfg = YamlConfiguration.loadConfiguration(this.file);
    }
    
    public abstract void setDefaults();
    
    public FileConfiguration getManager() {
        if (this.cfg == null) {
            this.cfg = YamlConfiguration.loadConfiguration(this.file);
        }
        return (FileConfiguration)this.cfg;
    }
    
    public void setValue(final String path, final int value) {
        if (this.cfg == null) {
            this.cfg = YamlConfiguration.loadConfiguration(this.file);
        }
        this.cfg.set(path, (Object)value);
        try {
            this.cfg.save(this.file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setObject(final String path, final Object object) {
        if (this.cfg == null) {
            this.cfg = YamlConfiguration.loadConfiguration(this.file);
        }
        this.cfg.set(path, object);
        this.save();
    }
    
    public Object getObject(final String path) {
        if (this.cfg == null) {
            this.cfg = YamlConfiguration.loadConfiguration(this.file);
        }
        return this.cfg.get(path);
    }
    
    public int getValue(final String path) {
        if (this.cfg == null) {
            this.cfg = YamlConfiguration.loadConfiguration(this.file);
        }
        return this.cfg.getInt(path);
    }
    
    public String getString(final String path) {
        if (this.cfg == null) {
            this.cfg = YamlConfiguration.loadConfiguration(this.file);
        }
        return this.cfg.getString(path);
    }
    
    public void setString(final String path, final String value) {
        if (this.cfg == null) {
            this.cfg = YamlConfiguration.loadConfiguration(this.file);
        }
        this.cfg.set(path, (Object)value);
        try {
            this.cfg.save(this.file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void save() {
        try {
            this.cfg.save(this.file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public File getFile() {
        return this.file;
    }
}