import { create } from 'zustand';
import { persist } from 'zustand/middleware';

interface MemberStore {
    member: any;
    setMember: (member: any) => void;
    removeMember: () => void;
}

const useStore = create<MemberStore>()(
    persist(
        (set) => ({
            member: null,
            setMember: (member) => {
                set((state) => ({ ...state, member }));
            },
            removeMember: () => {
                set((state) => ({ ...state, member: null }));
            },
        }),
        {
            name: 'member-store',
        }
    )
);

export default useStore;
