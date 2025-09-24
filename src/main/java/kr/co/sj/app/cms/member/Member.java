package kr.co.sj.app.cms.member;

import java.io.Serializable;
import java.util.Date;

public class Member implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean admin = false;		// 관리자 여부
	private boolean isLogin;
	private boolean anonymous = false;		// 게스트 여부

	private String member_id;  //사용자_아이디
	private String member_name;  //사용자_이름
	private String member_pw;  //패스워드
	private String memberNewPw;  //패스워드
	private String birth_day;  //생년월일
	private String email;  //이메일
	private String email1;  //이메일
	private String email2;  //이메일
	private String email_domain;  //이메일 도메인주소
	private String zipcode;  //우편번호
	private String address1;  //주소
	private String address2;  //동이하 주소
	private String phone;  //전화번호
	private String phone1;  //전화번호
	private String phone2;  //전화번호
	private String phone3;  //전화번호
	private String cell_phone;  //휴대폰
	private String cell_phone1;  //휴대폰
	private String cell_phone2;  //휴대폰
	private String cell_phone3;  //휴대폰
	private String sms_service_yn;  //SMS수신여부
	private String email_service_yn;  //EMAIL수신여부
	private String add_ip;  //등록IP
	private Date add_date;  //등록날짜
	private Date pw_change_date;  //비밀번호 변경일
	private Date last_login;  //마지막 로그인
	private String last_login_str;
	private String last_login_ip;
	private String sex;
	private String age;
	private String unAgreeFlag;
	private String unAgreeDate;

	private String search_auth;
	private String search_auth_name;

	private int history_idx;  //변경이력IDX
	private String use_yn;  //허용여부
	private String in_ip;  //등록IP
	private Date in_date;  //등록날짜
	private Date up_date;  //수정날짜
	private String auth_id;  //권한
	private String auth_id_list;  //권한 리스트 (다중)
	private String auth_name; //권한명
	private String auth_name_list;  // 권한 명 리스트 (화면용)
	private String modify_id;  //수정자ID
	private Date modify_date;  //수정일
	private String modify_ip;  //수정자IP

	private String safe_user_no; //안심회원번호

	private String password_expiry_day;
	private String seq_no;
	private String user_no; // 이용자 대출번호
	private String user_class;// 불량회원구분 (0 : 정상, 1 : 대출정지, 2 : 제적, 3 : 탈퇴)
	private String kl_member_yn;//책이음회원여부 (Y : 책이음회원, N or null : 일반회원)
	private String user_class_code;//신청자 대출직급정보코드
	private String agreement_yn;//개인정보 동의정보 존재여부 (Y/N)
	private String rec_key;//이용자KEY
	private String agree_yn;//개인정보 재동의여부 (Y : 재동의 대상 아님, N : 동의일자가  오늘 이전이어서 재동의 필요)
	private String cert_yn;//CI 존재여부 (Y/N)
	private String expiredate_yn;//개인정보만료일 유무 (Y/N)
	private String loan_stop_date; //대출정지만기일 (YYYY/MM/DD)	- 오늘 이후의 날짜로 저장되어 있는 경우에만 값이 반환되며, 그 이외에는 NULL
	private String overdue_cnt; //대출연체 권수
	private String local_loanable_cnt; //(해당 회원 직급의) 자관대출가능권수
	private String unity_loanable_cnt; //(해당 회원 직급의) 통합대출가능권수
	private String local_loan_cnt;//자관대출중권수
	private String unity_loan_cnt;//통합대출중권수
	private String lost_card_yn;//회원증분실여부 (Y/N)
	private String member_class;//회원구분 (0 : 정회원, 1 : 비회원, 2 : 준회원)
	private String user_position_code;//이용자 소속정보코드
	private String user_manage_code;//이용자 가입도서관 관리구분코드

	private String card_no;
	private String mobile_no; // 이용자 폰
	private String web_id; // WEB_ID
	private String status_code; // 1 - 탈퇴 or 웹회원 , 0 - 그외 이용자(정상적인이용자)
	private String loca; // 소장처코드
	private String loca_name; // 소장처

	private String di_value;
	private String ci_value;

	private String agree_codes;
	private String user_position;
	private Date agree_date;
	private String agree_date_str;
	private Date agree_expire_date;
	private String change_password_flag; // 비밀번호 변경여부
	private String company_name;//근무지명
	private String company_zipcode;//근무지우편번호
	private String company_addr;//근무지주소
	private String company_depart;//근무지부서명
	private String company_phone;
	private String company_phone1;
	private String company_phone2;
	private String company_phone3;
	private String parent_name;
	private String parent_phone;
	private String parent_phone1;
	private String parent_phone2;
	private String parent_phone3;

	private String loginCode;
	private String loginMsg;

	private String loginType;

	private String link_member_yn;  //다른시스템 계정 링크

	private String ageType;//14세미만:under, 14세이상:more
	private String certType;//gpin, sms
	private boolean certComplete;//인증 성공여부
	private String sci_result;

	private String integrationId;//회원통합시 선택값
	private String integrationIdList;//회원통합시 선택값
	private String integrationSeqNo;//회원통합시 선택값
	private String integrationSeqNoList;//회원통합시 선택값

	private String langMode = "kor";

	private String statement_alias;
	private String user_id;  //
	private String password;  //
	private int fid_key;  //
	private String name;  //
	private String idx_user_name;  //
	private Date birthday;  //
	private String birthday_type = "+";  //양력, 음력 구분. 기존홈페이지에서는 모두 양력으로 입력
	private String civil_no;  //주민번호. 컬럼은 존재하나 사용하지 않음. 회원가입시에도 입력하지 않음.
	private String h_addr1;  //집주소
	private String w_addr1;  //근무지주소
	private String w_addr2;  //근무지주소
	private String h_zipcode;  //집우편번호
	private String w_zipcode;  //근무지우편번호
	private String h_phone;  //집전화번호
	private String w_phone;  //근무지전화번호
	private String w_phone1;  //근무지전화번호
	private String w_phone2;  //근무지전화번호
	private String w_phone3;  //근무지전화번호
	private String exchange_no;  //
	private String department_name;  //부서
	private String class_name;  //
	private String handphone;  //휴대전화번호
	private String idx_handphone;  //
	private String e_mail;  //
	private Date reg_date;  //가입일
	private Date remove_date;  //
	private String inferior_reason;  //
	private String member_class_str;  //0:정회원, 2:준회원, 1:비회원
	private String school;  //학교
	private String grade;  //학년
	private String class_;  //반
	private String class_no;  //번호
	private String mail_send_area;  //기본우편물수령지 H:집주소, W:근무지주소 20171119 개발시점 기준 활용하지 않음(기존소스에도)
	private int loan_count;  //
	private int reservation_count;  //
	private int delay_count;  //
	private int delayday_count;  //
	private String loan_stop_date_str;  //대출정지일 문자열 YYYY년MM월DD일
	private Date last_loan_date;  //
	private Date last_modify_date;  //
	private String remark;  //
	private int reissue_cnt;  //
	private Date loan_stop_set_date;  //
	private String loan_stop_set_date_str;  //
	private Date new_user_mail_date;  //
	private String center_upload_check;  //
	private String shelf_loc_code;  //
	private String lost_user_card;  //
	private String sms_use_yn;  //
	private String moneta_inform_phone_num;  //
	private Date moneta_inform_date;  //
	private String card_password;  //
	private String first_work;  //
	private String last_work;  //
	private String user_define_code1;  //
	private String user_define_code2;  //
	private String user_define_code3;  //
	private String checking;  //
	private String office;  //근무처
	private String workno;  //
	private String rfid_serial;  //
	private String army_class;  //
	private String mailing_use_yn;  //
	private String mailing_use_yn2;  //
	private Date secede_date;  //
	private String pb_workno;  //
	private String manage_code;  // 도서관코드 영문 2자리
	private String password_q;  //
	private String password_a;  //
	private int ill_expire_return_cnt;  //
	private Date ill_apl_stop_set_date;  //
	private Date ill_apl_stop_date;  //
	private String self_loan_station_limit;  //
	private Date remark_edit_date;  //
	private String family_id;  //
	private String check_id_yn;  //
	private String gpin_ascii;  //
	private String gpin_hash;  //
	private String gpin_sex;  //
	private String crypto_civil_no;  //
	private String second_phone;  //
	private String idx_user_id;  //
	private Date reservation_stop_date;  //
	private Date private_info_date;  //
	private Date kl_reg_date;  //
	private String ipin_hash;  //실제론 DI값 들어감
	private Date ipin_date;  //
	private String ipin_result;  //1:인증완료
	private String certify_worker;  //인증구분 IPIN, phone
	private Date renewal_date;  //
	private Date privacy_destroy_date;  //
	private String privacy_confirm_yn;  //
	private String privacy_alarm;  //
	private String poor_applicant_yn;  //
	private String poor_applicant_reason;  //
	private String expelled_reason;  //
	private String channel_code;
	private String data;
	private String facility_id;
	private String rfid_yn;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getMember_pw() {
        return member_pw;
    }

    public void setMember_pw(String member_pw) {
        this.member_pw = member_pw;
    }

    public String getMemberNewPw() {
        return memberNewPw;
    }

    public void setMemberNewPw(String memberNewPw) {
        this.memberNewPw = memberNewPw;
    }

    public String getBirth_day() {
        return birth_day;
    }

    public void setBirth_day(String birth_day) {
        this.birth_day = birth_day;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getEmail_domain() {
        return email_domain;
    }

    public void setEmail_domain(String email_domain) {
        this.email_domain = email_domain;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public String getCell_phone() {
        return cell_phone;
    }

    public void setCell_phone(String cell_phone) {
        this.cell_phone = cell_phone;
    }

    public String getCell_phone1() {
        return cell_phone1;
    }

    public void setCell_phone1(String cell_phone1) {
        this.cell_phone1 = cell_phone1;
    }

    public String getCell_phone2() {
        return cell_phone2;
    }

    public void setCell_phone2(String cell_phone2) {
        this.cell_phone2 = cell_phone2;
    }

    public String getCell_phone3() {
        return cell_phone3;
    }

    public void setCell_phone3(String cell_phone3) {
        this.cell_phone3 = cell_phone3;
    }

    public String getSms_service_yn() {
        return sms_service_yn;
    }

    public void setSms_service_yn(String sms_service_yn) {
        this.sms_service_yn = sms_service_yn;
    }

    public String getEmail_service_yn() {
        return email_service_yn;
    }

    public void setEmail_service_yn(String email_service_yn) {
        this.email_service_yn = email_service_yn;
    }

    public String getAdd_ip() {
        return add_ip;
    }

    public void setAdd_ip(String add_ip) {
        this.add_ip = add_ip;
    }

    public Date getAdd_date() {
        return add_date;
    }

    public void setAdd_date(Date add_date) {
        this.add_date = add_date;
    }

    public Date getPw_change_date() {
        return pw_change_date;
    }

    public void setPw_change_date(Date pw_change_date) {
        this.pw_change_date = pw_change_date;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getLast_login_str() {
        return last_login_str;
    }

    public void setLast_login_str(String last_login_str) {
        this.last_login_str = last_login_str;
    }

    public String getLast_login_ip() {
        return last_login_ip;
    }

    public void setLast_login_ip(String last_login_ip) {
        this.last_login_ip = last_login_ip;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUnAgreeFlag() {
        return unAgreeFlag;
    }

    public void setUnAgreeFlag(String unAgreeFlag) {
        this.unAgreeFlag = unAgreeFlag;
    }

    public String getUnAgreeDate() {
        return unAgreeDate;
    }

    public void setUnAgreeDate(String unAgreeDate) {
        this.unAgreeDate = unAgreeDate;
    }

    public String getSearch_auth() {
        return search_auth;
    }

    public void setSearch_auth(String search_auth) {
        this.search_auth = search_auth;
    }

    public String getSearch_auth_name() {
        return search_auth_name;
    }

    public void setSearch_auth_name(String search_auth_name) {
        this.search_auth_name = search_auth_name;
    }

    public int getHistory_idx() {
        return history_idx;
    }

    public void setHistory_idx(int history_idx) {
        this.history_idx = history_idx;
    }

    public String getUse_yn() {
        return use_yn;
    }

    public void setUse_yn(String use_yn) {
        this.use_yn = use_yn;
    }

    public String getIn_ip() {
        return in_ip;
    }

    public void setIn_ip(String in_ip) {
        this.in_ip = in_ip;
    }

    public Date getIn_date() {
        return in_date;
    }

    public void setIn_date(Date in_date) {
        this.in_date = in_date;
    }

    public Date getUp_date() {
        return up_date;
    }

    public void setUp_date(Date up_date) {
        this.up_date = up_date;
    }

    public String getAuth_id() {
        return auth_id;
    }

    public void setAuth_id(String auth_id) {
        this.auth_id = auth_id;
    }

    public String getAuth_id_list() {
        return auth_id_list;
    }

    public void setAuth_id_list(String auth_id_list) {
        this.auth_id_list = auth_id_list;
    }

    public String getAuth_name() {
        return auth_name;
    }

    public void setAuth_name(String auth_name) {
        this.auth_name = auth_name;
    }

    public String getAuth_name_list() {
        return auth_name_list;
    }

    public void setAuth_name_list(String auth_name_list) {
        this.auth_name_list = auth_name_list;
    }

    public String getModify_id() {
        return modify_id;
    }

    public void setModify_id(String modify_id) {
        this.modify_id = modify_id;
    }

    public Date getModify_date() {
        return modify_date;
    }

    public void setModify_date(Date modify_date) {
        this.modify_date = modify_date;
    }

    public String getModify_ip() {
        return modify_ip;
    }

    public void setModify_ip(String modify_ip) {
        this.modify_ip = modify_ip;
    }

    public String getSafe_user_no() {
        return safe_user_no;
    }

    public void setSafe_user_no(String safe_user_no) {
        this.safe_user_no = safe_user_no;
    }

    public String getPassword_expiry_day() {
        return password_expiry_day;
    }

    public void setPassword_expiry_day(String password_expiry_day) {
        this.password_expiry_day = password_expiry_day;
    }

    public String getSeq_no() {
        return seq_no;
    }

    public void setSeq_no(String seq_no) {
        this.seq_no = seq_no;
    }

    public String getUser_no() {
        return user_no;
    }

    public void setUser_no(String user_no) {
        this.user_no = user_no;
    }

    public String getUser_class() {
        return user_class;
    }

    public void setUser_class(String user_class) {
        this.user_class = user_class;
    }

    public String getKl_member_yn() {
        return kl_member_yn;
    }

    public void setKl_member_yn(String kl_member_yn) {
        this.kl_member_yn = kl_member_yn;
    }

    public String getUser_class_code() {
        return user_class_code;
    }

    public void setUser_class_code(String user_class_code) {
        this.user_class_code = user_class_code;
    }

    public String getAgreement_yn() {
        return agreement_yn;
    }

    public void setAgreement_yn(String agreement_yn) {
        this.agreement_yn = agreement_yn;
    }

    public String getRec_key() {
        return rec_key;
    }

    public void setRec_key(String rec_key) {
        this.rec_key = rec_key;
    }

    public String getAgree_yn() {
        return agree_yn;
    }

    public void setAgree_yn(String agree_yn) {
        this.agree_yn = agree_yn;
    }

    public String getCert_yn() {
        return cert_yn;
    }

    public void setCert_yn(String cert_yn) {
        this.cert_yn = cert_yn;
    }

    public String getExpiredate_yn() {
        return expiredate_yn;
    }

    public void setExpiredate_yn(String expiredate_yn) {
        this.expiredate_yn = expiredate_yn;
    }

    public String getLoan_stop_date() {
        return loan_stop_date;
    }

    public void setLoan_stop_date(String loan_stop_date) {
        this.loan_stop_date = loan_stop_date;
    }

    public String getOverdue_cnt() {
        return overdue_cnt;
    }

    public void setOverdue_cnt(String overdue_cnt) {
        this.overdue_cnt = overdue_cnt;
    }

    public String getLocal_loanable_cnt() {
        return local_loanable_cnt;
    }

    public void setLocal_loanable_cnt(String local_loanable_cnt) {
        this.local_loanable_cnt = local_loanable_cnt;
    }

    public String getUnity_loanable_cnt() {
        return unity_loanable_cnt;
    }

    public void setUnity_loanable_cnt(String unity_loanable_cnt) {
        this.unity_loanable_cnt = unity_loanable_cnt;
    }

    public String getLocal_loan_cnt() {
        return local_loan_cnt;
    }

    public void setLocal_loan_cnt(String local_loan_cnt) {
        this.local_loan_cnt = local_loan_cnt;
    }

    public String getUnity_loan_cnt() {
        return unity_loan_cnt;
    }

    public void setUnity_loan_cnt(String unity_loan_cnt) {
        this.unity_loan_cnt = unity_loan_cnt;
    }

    public String getLost_card_yn() {
        return lost_card_yn;
    }

    public void setLost_card_yn(String lost_card_yn) {
        this.lost_card_yn = lost_card_yn;
    }

    public String getMember_class() {
        return member_class;
    }

    public void setMember_class(String member_class) {
        this.member_class = member_class;
    }

    public String getUser_position_code() {
        return user_position_code;
    }

    public void setUser_position_code(String user_position_code) {
        this.user_position_code = user_position_code;
    }

    public String getUser_manage_code() {
        return user_manage_code;
    }

    public void setUser_manage_code(String user_manage_code) {
        this.user_manage_code = user_manage_code;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getWeb_id() {
        return web_id;
    }

    public void setWeb_id(String web_id) {
        this.web_id = web_id;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getLoca() {
        return loca;
    }

    public void setLoca(String loca) {
        this.loca = loca;
    }

    public String getLoca_name() {
        return loca_name;
    }

    public void setLoca_name(String loca_name) {
        this.loca_name = loca_name;
    }

    public String getDi_value() {
        return di_value;
    }

    public void setDi_value(String di_value) {
        this.di_value = di_value;
    }

    public String getCi_value() {
        return ci_value;
    }

    public void setCi_value(String ci_value) {
        this.ci_value = ci_value;
    }

    public String getAgree_codes() {
        return agree_codes;
    }

    public void setAgree_codes(String agree_codes) {
        this.agree_codes = agree_codes;
    }

    public String getUser_position() {
        return user_position;
    }

    public void setUser_position(String user_position) {
        this.user_position = user_position;
    }

    public Date getAgree_date() {
        return agree_date;
    }

    public void setAgree_date(Date agree_date) {
        this.agree_date = agree_date;
    }

    public String getAgree_date_str() {
        return agree_date_str;
    }

    public void setAgree_date_str(String agree_date_str) {
        this.agree_date_str = agree_date_str;
    }

    public Date getAgree_expire_date() {
        return agree_expire_date;
    }

    public void setAgree_expire_date(Date agree_expire_date) {
        this.agree_expire_date = agree_expire_date;
    }

    public String getChange_password_flag() {
        return change_password_flag;
    }

    public void setChange_password_flag(String change_password_flag) {
        this.change_password_flag = change_password_flag;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_zipcode() {
        return company_zipcode;
    }

    public void setCompany_zipcode(String company_zipcode) {
        this.company_zipcode = company_zipcode;
    }

    public String getCompany_addr() {
        return company_addr;
    }

    public void setCompany_addr(String company_addr) {
        this.company_addr = company_addr;
    }

    public String getCompany_depart() {
        return company_depart;
    }

    public void setCompany_depart(String company_depart) {
        this.company_depart = company_depart;
    }

    public String getCompany_phone() {
        return company_phone;
    }

    public void setCompany_phone(String company_phone) {
        this.company_phone = company_phone;
    }

    public String getCompany_phone1() {
        return company_phone1;
    }

    public void setCompany_phone1(String company_phone1) {
        this.company_phone1 = company_phone1;
    }

    public String getCompany_phone2() {
        return company_phone2;
    }

    public void setCompany_phone2(String company_phone2) {
        this.company_phone2 = company_phone2;
    }

    public String getCompany_phone3() {
        return company_phone3;
    }

    public void setCompany_phone3(String company_phone3) {
        this.company_phone3 = company_phone3;
    }

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    public String getParent_phone() {
        return parent_phone;
    }

    public void setParent_phone(String parent_phone) {
        this.parent_phone = parent_phone;
    }

    public String getParent_phone1() {
        return parent_phone1;
    }

    public void setParent_phone1(String parent_phone1) {
        this.parent_phone1 = parent_phone1;
    }

    public String getParent_phone2() {
        return parent_phone2;
    }

    public void setParent_phone2(String parent_phone2) {
        this.parent_phone2 = parent_phone2;
    }

    public String getParent_phone3() {
        return parent_phone3;
    }

    public void setParent_phone3(String parent_phone3) {
        this.parent_phone3 = parent_phone3;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getLoginMsg() {
        return loginMsg;
    }

    public void setLoginMsg(String loginMsg) {
        this.loginMsg = loginMsg;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getLink_member_yn() {
        return link_member_yn;
    }

    public void setLink_member_yn(String link_member_yn) {
        this.link_member_yn = link_member_yn;
    }

    public String getAgeType() {
        return ageType;
    }

    public void setAgeType(String ageType) {
        this.ageType = ageType;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public boolean isCertComplete() {
        return certComplete;
    }

    public void setCertComplete(boolean certComplete) {
        this.certComplete = certComplete;
    }

    public String getSci_result() {
        return sci_result;
    }

    public void setSci_result(String sci_result) {
        this.sci_result = sci_result;
    }

    public String getIntegrationId() {
        return integrationId;
    }

    public void setIntegrationId(String integrationId) {
        this.integrationId = integrationId;
    }

    public String getIntegrationIdList() {
        return integrationIdList;
    }

    public void setIntegrationIdList(String integrationIdList) {
        this.integrationIdList = integrationIdList;
    }

    public String getIntegrationSeqNo() {
        return integrationSeqNo;
    }

    public void setIntegrationSeqNo(String integrationSeqNo) {
        this.integrationSeqNo = integrationSeqNo;
    }

    public String getIntegrationSeqNoList() {
        return integrationSeqNoList;
    }

    public void setIntegrationSeqNoList(String integrationSeqNoList) {
        this.integrationSeqNoList = integrationSeqNoList;
    }

    public String getLangMode() {
        return langMode;
    }

    public void setLangMode(String langMode) {
        this.langMode = langMode;
    }

    public String getStatement_alias() {
        return statement_alias;
    }

    public void setStatement_alias(String statement_alias) {
        this.statement_alias = statement_alias;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getFid_key() {
        return fid_key;
    }

    public void setFid_key(int fid_key) {
        this.fid_key = fid_key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdx_user_name() {
        return idx_user_name;
    }

    public void setIdx_user_name(String idx_user_name) {
        this.idx_user_name = idx_user_name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getBirthday_type() {
        return birthday_type;
    }

    public void setBirthday_type(String birthday_type) {
        this.birthday_type = birthday_type;
    }

    public String getCivil_no() {
        return civil_no;
    }

    public void setCivil_no(String civil_no) {
        this.civil_no = civil_no;
    }

    public String getH_addr1() {
        return h_addr1;
    }

    public void setH_addr1(String h_addr1) {
        this.h_addr1 = h_addr1;
    }

    public String getW_addr1() {
        return w_addr1;
    }

    public void setW_addr1(String w_addr1) {
        this.w_addr1 = w_addr1;
    }

    public String getW_addr2() {
        return w_addr2;
    }

    public void setW_addr2(String w_addr2) {
        this.w_addr2 = w_addr2;
    }

    public String getH_zipcode() {
        return h_zipcode;
    }

    public void setH_zipcode(String h_zipcode) {
        this.h_zipcode = h_zipcode;
    }

    public String getW_zipcode() {
        return w_zipcode;
    }

    public void setW_zipcode(String w_zipcode) {
        this.w_zipcode = w_zipcode;
    }

    public String getH_phone() {
        return h_phone;
    }

    public void setH_phone(String h_phone) {
        this.h_phone = h_phone;
    }

    public String getW_phone() {
        return w_phone;
    }

    public void setW_phone(String w_phone) {
        this.w_phone = w_phone;
    }

    public String getW_phone1() {
        return w_phone1;
    }

    public void setW_phone1(String w_phone1) {
        this.w_phone1 = w_phone1;
    }

    public String getW_phone2() {
        return w_phone2;
    }

    public void setW_phone2(String w_phone2) {
        this.w_phone2 = w_phone2;
    }

    public String getW_phone3() {
        return w_phone3;
    }

    public void setW_phone3(String w_phone3) {
        this.w_phone3 = w_phone3;
    }

    public String getExchange_no() {
        return exchange_no;
    }

    public void setExchange_no(String exchange_no) {
        this.exchange_no = exchange_no;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getHandphone() {
        return handphone;
    }

    public void setHandphone(String handphone) {
        this.handphone = handphone;
    }

    public String getIdx_handphone() {
        return idx_handphone;
    }

    public void setIdx_handphone(String idx_handphone) {
        this.idx_handphone = idx_handphone;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public Date getRemove_date() {
        return remove_date;
    }

    public void setRemove_date(Date remove_date) {
        this.remove_date = remove_date;
    }

    public String getInferior_reason() {
        return inferior_reason;
    }

    public void setInferior_reason(String inferior_reason) {
        this.inferior_reason = inferior_reason;
    }

    public String getMember_class_str() {
        return member_class_str;
    }

    public void setMember_class_str(String member_class_str) {
        this.member_class_str = member_class_str;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClass_() {
        return class_;
    }

    public void setClass_(String class_) {
        this.class_ = class_;
    }

    public String getClass_no() {
        return class_no;
    }

    public void setClass_no(String class_no) {
        this.class_no = class_no;
    }

    public String getMail_send_area() {
        return mail_send_area;
    }

    public void setMail_send_area(String mail_send_area) {
        this.mail_send_area = mail_send_area;
    }

    public int getLoan_count() {
        return loan_count;
    }

    public void setLoan_count(int loan_count) {
        this.loan_count = loan_count;
    }

    public int getReservation_count() {
        return reservation_count;
    }

    public void setReservation_count(int reservation_count) {
        this.reservation_count = reservation_count;
    }

    public int getDelay_count() {
        return delay_count;
    }

    public void setDelay_count(int delay_count) {
        this.delay_count = delay_count;
    }

    public int getDelayday_count() {
        return delayday_count;
    }

    public void setDelayday_count(int delayday_count) {
        this.delayday_count = delayday_count;
    }

    public String getLoan_stop_date_str() {
        return loan_stop_date_str;
    }

    public void setLoan_stop_date_str(String loan_stop_date_str) {
        this.loan_stop_date_str = loan_stop_date_str;
    }

    public Date getLast_loan_date() {
        return last_loan_date;
    }

    public void setLast_loan_date(Date last_loan_date) {
        this.last_loan_date = last_loan_date;
    }

    public Date getLast_modify_date() {
        return last_modify_date;
    }

    public void setLast_modify_date(Date last_modify_date) {
        this.last_modify_date = last_modify_date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getReissue_cnt() {
        return reissue_cnt;
    }

    public void setReissue_cnt(int reissue_cnt) {
        this.reissue_cnt = reissue_cnt;
    }

    public Date getLoan_stop_set_date() {
        return loan_stop_set_date;
    }

    public void setLoan_stop_set_date(Date loan_stop_set_date) {
        this.loan_stop_set_date = loan_stop_set_date;
    }

    public String getLoan_stop_set_date_str() {
        return loan_stop_set_date_str;
    }

    public void setLoan_stop_set_date_str(String loan_stop_set_date_str) {
        this.loan_stop_set_date_str = loan_stop_set_date_str;
    }

    public Date getNew_user_mail_date() {
        return new_user_mail_date;
    }

    public void setNew_user_mail_date(Date new_user_mail_date) {
        this.new_user_mail_date = new_user_mail_date;
    }

    public String getCenter_upload_check() {
        return center_upload_check;
    }

    public void setCenter_upload_check(String center_upload_check) {
        this.center_upload_check = center_upload_check;
    }

    public String getShelf_loc_code() {
        return shelf_loc_code;
    }

    public void setShelf_loc_code(String shelf_loc_code) {
        this.shelf_loc_code = shelf_loc_code;
    }

    public String getLost_user_card() {
        return lost_user_card;
    }

    public void setLost_user_card(String lost_user_card) {
        this.lost_user_card = lost_user_card;
    }

    public String getSms_use_yn() {
        return sms_use_yn;
    }

    public void setSms_use_yn(String sms_use_yn) {
        this.sms_use_yn = sms_use_yn;
    }

    public String getMoneta_inform_phone_num() {
        return moneta_inform_phone_num;
    }

    public void setMoneta_inform_phone_num(String moneta_inform_phone_num) {
        this.moneta_inform_phone_num = moneta_inform_phone_num;
    }

    public Date getMoneta_inform_date() {
        return moneta_inform_date;
    }

    public void setMoneta_inform_date(Date moneta_inform_date) {
        this.moneta_inform_date = moneta_inform_date;
    }

    public String getCard_password() {
        return card_password;
    }

    public void setCard_password(String card_password) {
        this.card_password = card_password;
    }

    public String getFirst_work() {
        return first_work;
    }

    public void setFirst_work(String first_work) {
        this.first_work = first_work;
    }

    public String getLast_work() {
        return last_work;
    }

    public void setLast_work(String last_work) {
        this.last_work = last_work;
    }

    public String getUser_define_code1() {
        return user_define_code1;
    }

    public void setUser_define_code1(String user_define_code1) {
        this.user_define_code1 = user_define_code1;
    }

    public String getUser_define_code2() {
        return user_define_code2;
    }

    public void setUser_define_code2(String user_define_code2) {
        this.user_define_code2 = user_define_code2;
    }

    public String getUser_define_code3() {
        return user_define_code3;
    }

    public void setUser_define_code3(String user_define_code3) {
        this.user_define_code3 = user_define_code3;
    }

    public String getChecking() {
        return checking;
    }

    public void setChecking(String checking) {
        this.checking = checking;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getWorkno() {
        return workno;
    }

    public void setWorkno(String workno) {
        this.workno = workno;
    }

    public String getRfid_serial() {
        return rfid_serial;
    }

    public void setRfid_serial(String rfid_serial) {
        this.rfid_serial = rfid_serial;
    }

    public String getArmy_class() {
        return army_class;
    }

    public void setArmy_class(String army_class) {
        this.army_class = army_class;
    }

    public String getMailing_use_yn() {
        return mailing_use_yn;
    }

    public void setMailing_use_yn(String mailing_use_yn) {
        this.mailing_use_yn = mailing_use_yn;
    }

    public String getMailing_use_yn2() {
        return mailing_use_yn2;
    }

    public void setMailing_use_yn2(String mailing_use_yn2) {
        this.mailing_use_yn2 = mailing_use_yn2;
    }

    public Date getSecede_date() {
        return secede_date;
    }

    public void setSecede_date(Date secede_date) {
        this.secede_date = secede_date;
    }

    public String getPb_workno() {
        return pb_workno;
    }

    public void setPb_workno(String pb_workno) {
        this.pb_workno = pb_workno;
    }

    public String getManage_code() {
        return manage_code;
    }

    public void setManage_code(String manage_code) {
        this.manage_code = manage_code;
    }

    public String getPassword_q() {
        return password_q;
    }

    public void setPassword_q(String password_q) {
        this.password_q = password_q;
    }

    public String getPassword_a() {
        return password_a;
    }

    public void setPassword_a(String password_a) {
        this.password_a = password_a;
    }

    public int getIll_expire_return_cnt() {
        return ill_expire_return_cnt;
    }

    public void setIll_expire_return_cnt(int ill_expire_return_cnt) {
        this.ill_expire_return_cnt = ill_expire_return_cnt;
    }

    public Date getIll_apl_stop_set_date() {
        return ill_apl_stop_set_date;
    }

    public void setIll_apl_stop_set_date(Date ill_apl_stop_set_date) {
        this.ill_apl_stop_set_date = ill_apl_stop_set_date;
    }

    public Date getIll_apl_stop_date() {
        return ill_apl_stop_date;
    }

    public void setIll_apl_stop_date(Date ill_apl_stop_date) {
        this.ill_apl_stop_date = ill_apl_stop_date;
    }

    public String getSelf_loan_station_limit() {
        return self_loan_station_limit;
    }

    public void setSelf_loan_station_limit(String self_loan_station_limit) {
        this.self_loan_station_limit = self_loan_station_limit;
    }

    public Date getRemark_edit_date() {
        return remark_edit_date;
    }

    public void setRemark_edit_date(Date remark_edit_date) {
        this.remark_edit_date = remark_edit_date;
    }

    public String getFamily_id() {
        return family_id;
    }

    public void setFamily_id(String family_id) {
        this.family_id = family_id;
    }

    public String getCheck_id_yn() {
        return check_id_yn;
    }

    public void setCheck_id_yn(String check_id_yn) {
        this.check_id_yn = check_id_yn;
    }

    public String getGpin_ascii() {
        return gpin_ascii;
    }

    public void setGpin_ascii(String gpin_ascii) {
        this.gpin_ascii = gpin_ascii;
    }

    public String getGpin_hash() {
        return gpin_hash;
    }

    public void setGpin_hash(String gpin_hash) {
        this.gpin_hash = gpin_hash;
    }

    public String getGpin_sex() {
        return gpin_sex;
    }

    public void setGpin_sex(String gpin_sex) {
        this.gpin_sex = gpin_sex;
    }

    public String getCrypto_civil_no() {
        return crypto_civil_no;
    }

    public void setCrypto_civil_no(String crypto_civil_no) {
        this.crypto_civil_no = crypto_civil_no;
    }

    public String getSecond_phone() {
        return second_phone;
    }

    public void setSecond_phone(String second_phone) {
        this.second_phone = second_phone;
    }

    public String getIdx_user_id() {
        return idx_user_id;
    }

    public void setIdx_user_id(String idx_user_id) {
        this.idx_user_id = idx_user_id;
    }

    public Date getReservation_stop_date() {
        return reservation_stop_date;
    }

    public void setReservation_stop_date(Date reservation_stop_date) {
        this.reservation_stop_date = reservation_stop_date;
    }

    public Date getPrivate_info_date() {
        return private_info_date;
    }

    public void setPrivate_info_date(Date private_info_date) {
        this.private_info_date = private_info_date;
    }

    public Date getKl_reg_date() {
        return kl_reg_date;
    }

    public void setKl_reg_date(Date kl_reg_date) {
        this.kl_reg_date = kl_reg_date;
    }

    public String getIpin_hash() {
        return ipin_hash;
    }

    public void setIpin_hash(String ipin_hash) {
        this.ipin_hash = ipin_hash;
    }

    public Date getIpin_date() {
        return ipin_date;
    }

    public void setIpin_date(Date ipin_date) {
        this.ipin_date = ipin_date;
    }

    public String getIpin_result() {
        return ipin_result;
    }

    public void setIpin_result(String ipin_result) {
        this.ipin_result = ipin_result;
    }

    public String getCertify_worker() {
        return certify_worker;
    }

    public void setCertify_worker(String certify_worker) {
        this.certify_worker = certify_worker;
    }

    public Date getRenewal_date() {
        return renewal_date;
    }

    public void setRenewal_date(Date renewal_date) {
        this.renewal_date = renewal_date;
    }

    public Date getPrivacy_destroy_date() {
        return privacy_destroy_date;
    }

    public void setPrivacy_destroy_date(Date privacy_destroy_date) {
        this.privacy_destroy_date = privacy_destroy_date;
    }

    public String getPrivacy_confirm_yn() {
        return privacy_confirm_yn;
    }

    public void setPrivacy_confirm_yn(String privacy_confirm_yn) {
        this.privacy_confirm_yn = privacy_confirm_yn;
    }

    public String getPrivacy_alarm() {
        return privacy_alarm;
    }

    public void setPrivacy_alarm(String privacy_alarm) {
        this.privacy_alarm = privacy_alarm;
    }

    public String getPoor_applicant_yn() {
        return poor_applicant_yn;
    }

    public void setPoor_applicant_yn(String poor_applicant_yn) {
        this.poor_applicant_yn = poor_applicant_yn;
    }

    public String getPoor_applicant_reason() {
        return poor_applicant_reason;
    }

    public void setPoor_applicant_reason(String poor_applicant_reason) {
        this.poor_applicant_reason = poor_applicant_reason;
    }

    public String getExpelled_reason() {
        return expelled_reason;
    }

    public void setExpelled_reason(String expelled_reason) {
        this.expelled_reason = expelled_reason;
    }

    public String getChannel_code() {
        return channel_code;
    }

    public void setChannel_code(String channel_code) {
        this.channel_code = channel_code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFacility_id() {
        return facility_id;
    }

    public void setFacility_id(String facility_id) {
        this.facility_id = facility_id;
    }

    public String getRfid_yn() {
        return rfid_yn;
    }

    public void setRfid_yn(String rfid_yn) {
        this.rfid_yn = rfid_yn;
    }
}
