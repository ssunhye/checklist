// setter

import { configs } from "eslint-plugin-prettier";

/*
export function setIsbookmarked(state, isbookmarked) {
  state.board.isbookmarked = isbookmarked;
}
*/

// 상품 아이디 저장
export function setProductId(state, productId) {
  state.productId = productId;
}

// 현재 클릭한 모달 이름 저장
export function setModal(state, modal) {
  state.modal = modal;
}

// 상단 검색어 저장
export function setSearchWord(state, searchWord) {
  state.searchWord = searchWord;
  var object = {
    category: state.searchCategory,
    word: state.searchWord,
  }
  // 최근 검색 히스토리 배열 앞에 추가
  state.searchHistory.unshift(object);
}

// 검색어 리스트 전체 삭제
export function deleteHistoryAll(state) {
  state.searchHistory = [];
}

// 특정 검색어 리스트 전체 삭제
export function deleteWord(state, index) {
  state.searchHistory.splice(index, 1);
}

// 메인페이지에서 카테고리 선택 -> 카테고리 이름 저장
export function setSelectCategoryName(state, id) {
  state.selectCategoryName = id;
}

// 상품의 정보 저장
export function setProductInfo(state, productInfo) {
  state.productInfo = productInfo;
}

// 리뷰 정보 저장
export function setReviewInfo(state, reviewInfo) {
  state.reviewInfo = reviewInfo;
}

// 최근 본 상품 저장
export function setProductHistory(state, history){
  // 가장 최근에 본 상품과 다를 때는 PASS
  var size = state.productHistory.length;
  if(size > 0 && state.productHistory[0].productInfo.pcode == history.productInfo.pcode)
    return;

  // 만약, 이미 4개의 상품의 저장되어있으면 가장 먼저 저장된 값 삭제
  var deleteCnt = size < 4 ? 0 :size-3;
  state.productHistory.splice(3, deleteCnt);

  // 최근 본 상품 배열 앞에 추가
  state.productHistory.unshift(history);
}

// 검색한 상품리스트 저장
export function setSearchProductListInfo(state, searchProductList) {
  state.searchProductList = searchProductList;
}

// 검색 상품 리스트 초기화
export function clearSearchProductListInfo(state) {
  state.searchProductList = [];
}

// 메인 체크픽 카테고리 저장
export function setMainCheckPickCategory(state, name) {
  state.mainCheckPickCategory = name;
}

// 선택한 탭 저장
export function setSearchTap(state, name) {
  state.searchTab = name;
}

// 선택한 필터 목록 저장
export function setSelectedFilter(state, selectedFilter) {
  state.selectedFilter = selectedFilter;
}

// 검색바 카테고리 저장
export function setSearchCategory(state, name) {
  state.searchCategory = name;
}

// 최근검색어창 변수 저장
export function setSearchHistoryView(state, history) {
  state.searchHistoryView = history;
}

// 워드클라우드 단어정보 저장
export function setWordCloudInfo(state, wordCloudInfo) {
  state.wordCloudInfo = wordCloudInfo;
}

// 체크픽 상품 10개 저장
export function setCheckPickList(state, list) {
  state.checkPickList = list;
}
