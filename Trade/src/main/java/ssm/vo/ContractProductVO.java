package ssm.vo;

import ssm.model.Contract;
import ssm.model.ExtCproduct;
import ssm.model.Factory;

import java.util.List;

public class ContractProductVO {
    private String id;

    private Contract contract;
    private Factory factory;
    private List<ExtCproduct> extCproducts;

    private String factoryName;
    private String productNo;
    private String productImage;
    private String productDesc;
    private Integer cnumber;
    private Integer outCnumber;
    private String loadingRate;
    private Integer boxNum;
    private String packingUnit;
    private Double price;
    private Double amount;
    private Integer finished;
    private String exts;
    private Integer orderNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public List<ExtCproduct> getExtCproducts() {
        return extCproducts;
    }

    public void setExtCproducts(List<ExtCproduct> extCproducts) {
        this.extCproducts = extCproducts;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Integer getCnumber() {
        return cnumber;
    }

    public void setCnumber(Integer cnumber) {
        this.cnumber = cnumber;
    }

    public Integer getOutCnumber() {
        return outCnumber;
    }

    public void setOutCnumber(Integer outCnumber) {
        this.outCnumber = outCnumber;
    }

    public String getLoadingRate() {
        return loadingRate;
    }

    public void setLoadingRate(String loadingRate) {
        this.loadingRate = loadingRate;
    }

    public Integer getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(Integer boxNum) {
        this.boxNum = boxNum;
    }

    public String getPackingUnit() {
        return packingUnit;
    }

    public void setPackingUnit(String packingUnit) {
        this.packingUnit = packingUnit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getFinished() {
        return finished;
    }

    public void setFinished(Integer finished) {
        this.finished = finished;
    }

    public String getExts() {
        return exts;
    }

    public void setExts(String exts) {
        this.exts = exts;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }
}
