package lk.icoder.excelreader.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @Project ceft-recon
 * @Author DILAN on 7/2/2021
 */
@Getter
@Setter
@RequiredArgsConstructor
public class AdjustmentDto {

    private String current_case_stage;
    private String case_no;
    private String stage_exp_date;
    private String pan;
    private String case_updated;
    private String tran_currency;
    private String tran_amount;
    private String reason_code;
    private String adjustment_currency;
    private String adjustment_amount;
    private String iss_recon_inst_id;
    private String tran_local_date_time;
    private String acq_recon_inst_id;
    private String retrieval_ref_no;
    private String proc_ntwk;
    private String act_to_iss;
    private String card_acpt_term_id;
    private String card_acpt_name;
    private String merch_rpting_level;
}
