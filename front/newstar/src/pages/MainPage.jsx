import { Routes, Route } from "react-router-dom"

import ShortForm from "./ShortForm"
import Search from "./Search"
import MyPage from "./MyPage"
import CategoryNews from "./CategoryNews"
import CategoryNewsDetail from "./CategoryNewsDetail"
import ChooseKeyword from "./ChooseKeyword"
import NewsDetail from "./NewsDetail"

import { ContainerWithNav } from "../styles/Container"
import { TopNavbar, BottomNavbar } from "../components/Navbar";

function MainPage() {
  return (
    <>
      <TopNavbar/>
      <ContainerWithNav>
        <Routes>
          <Route path="/" element={<ShortForm/>} />
          <Route path="/:articleId" element={<NewsDetail/>} />
          <Route path="/search" element={<Search/>} />
          <Route path="/mypage" element={<MyPage/>} />
          <Route path="/category" element={<CategoryNews/>} />
          <Route path="/category/:categoryId" element={<CategoryNewsDetail/>} />
          <Route path="/keywords" element={<ChooseKeyword/>} />
        </Routes>
      </ContainerWithNav>
      <BottomNavbar/>
    </>
  )
}

export default MainPage