package lk.icoder.excelreader.config;

import lk.icoder.excelreader.model.AdjustmentDto;
import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.support.rowset.RowSet;

/**
 * @Project excel-reader
 * @Author DILAN on 10/12/2021
 */
public class AdjustmentExcelRowMapper implements RowMapper<AdjustmentDto> {

    @Override
    public AdjustmentDto mapRow(RowSet rowSet) throws Exception {
        AdjustmentDto adjustmentDto = new AdjustmentDto();
        adjustmentDto.setCurrent_case_stage(rowSet.getCurrentRow()[1]);
        adjustmentDto.setCase_no(rowSet.getCurrentRow()[2]);
        adjustmentDto.setStage_exp_date(rowSet.getCurrentRow()[3]);
        adjustmentDto.setPan(rowSet.getCurrentRow()[4]);
        adjustmentDto.setCase_updated(rowSet.getCurrentRow()[5]);
        adjustmentDto.setTran_currency(rowSet.getCurrentRow()[6]);
        adjustmentDto.setTran_amount(rowSet.getCurrentRow()[7]);
        adjustmentDto.setReason_code(rowSet.getCurrentRow()[8]);
        adjustmentDto.setAdjustment_currency(rowSet.getCurrentRow()[9]);
        adjustmentDto.setAdjustment_currency(rowSet.getCurrentRow()[10]);
        adjustmentDto.setIss_recon_inst_id(rowSet.getCurrentRow()[11]);
        adjustmentDto.setTran_local_date_time(rowSet.getCurrentRow()[12]);
        adjustmentDto.setAcq_recon_inst_id(rowSet.getCurrentRow()[13]);
        adjustmentDto.setRetrieval_ref_no(rowSet.getCurrentRow()[14]);
        adjustmentDto.setProc_ntwk(rowSet.getCurrentRow()[15]);
        adjustmentDto.setAct_to_iss(rowSet.getCurrentRow()[16]);
        adjustmentDto.setCard_acpt_term_id(rowSet.getCurrentRow()[17]);
        adjustmentDto.setCard_acpt_name(rowSet.getCurrentRow()[18]);
        adjustmentDto.setMerch_rpting_level(rowSet.getCurrentRow()[19]);

        return adjustmentDto;
    }
}
